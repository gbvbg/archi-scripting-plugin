package com.archimatetool.script;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

public class ExpiryCheck {

    // NOTE month = 0-11
    static int year = 2018, month = 6, day = 22;
    
    static String message1 = "This version of jArchi has expired. Please download the latest version or uninstall this version.";
    static String message2 = "You can uninstall jArchi by deleting files {Archi Install Dir}/plugins/com.archimatetool.script.*";
    
    public static boolean hasExpired() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return new Date().after(calendar.getTime());
    }
    
    public static void showMessage(Composite parent) {
        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        CLabel imageLabel = new CLabel(composite, SWT.NULL);
        imageLabel.setImage(Display.getDefault().getSystemImage(SWT.ICON_WARNING));
        imageLabel.setText(message1);
        
        Label label = new Label(composite, SWT.NULL);
        label.setText(message2);
    }
}
