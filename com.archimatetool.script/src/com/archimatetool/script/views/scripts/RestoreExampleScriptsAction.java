/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script.views.scripts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

import com.archimatetool.script.ArchiScriptPlugin;
import com.archimatetool.script.IArchiScriptImages;


/**
 * Restore the Example Scripts in the User Folder
 */
public class RestoreExampleScriptsAction extends Action {
    
    private TreeViewer fViewer;
    
    public RestoreExampleScriptsAction(TreeViewer viewer) {
        fViewer = viewer;
        setText(Messages.RestoreExampleScriptsAction_0);
        setToolTipText(Messages.RestoreExampleScriptsAction_1);
        setImageDescriptor(IArchiScriptImages.ImageFactory.getImageDescriptor(IArchiScriptImages.ICON_EXAMPLES));
    }

    @Override
    public void run() {
        Enumeration<URL> enm = ArchiScriptPlugin.INSTANCE.getBundle().findEntries("examples", //$NON-NLS-1$
                ArchiScriptPlugin.SCRIPT_WILDCARD_EXTENSION, true);
        
        if(enm == null) {
            return;
        }
        
        try {
            while(enm.hasMoreElements()) {
                URL url = enm.nextElement();
                
                File filePath = new File(ArchiScriptPlugin.INSTANCE.getUserScriptsFolder(), url.getPath());
                filePath.mkdirs();
                
                InputStream in = url.openStream();
                Files.copy(in, new File(ArchiScriptPlugin.INSTANCE.getUserScriptsFolder(), url.getFile()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                in.close();
            }
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        finally {
            fViewer.refresh();
        }
    }
}