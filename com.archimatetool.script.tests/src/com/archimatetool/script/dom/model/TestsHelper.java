/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.script.dom.model;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.resource.Resource;

import com.archimatetool.model.IArchimateModel;
import com.archimatetool.model.util.ArchimateResourceFactory;

/**
 * Helper
 * 
 * @author Phillip Beauvoir
 */
@SuppressWarnings("nls")
public class TestsHelper {
    
    private static File testFolder;
    
    static File TEST_MODEL_FILE_ARCHISURANCE = new File(getTestDataFolder(), "Archisurance.archimate");

    static File getTestDataFolder() {
        if(testFolder == null) {
            testFolder = getLocalBundleFolder("com.archimatetool.script.tests", "testdata");
        }
        return testFolder;
    }

    static ArchimateModelProxy loadTestModel(File file) {
        try {
            Resource resource = ArchimateResourceFactory.createNewResource(file);
            resource.load(null);
            IArchimateModel model = (IArchimateModel)resource.getContents().get(0);
            model.setFile(file);
            return (ArchimateModelProxy)EObjectProxy.get(model);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    private static File getLocalBundleFolder(String bundleName, String path) {
        URL url = Platform.getBundle(bundleName).getEntry("/");
        try {
            url = FileLocator.resolve(url);
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return new File(url.getPath(), path);
    }
}
