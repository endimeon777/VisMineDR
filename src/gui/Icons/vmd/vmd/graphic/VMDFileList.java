package gui.Icons.vmd.vmd.graphic;

import java.io.File;
import java.io.FilenameFilter;

/* Source from:
 * Addison-Wesley. (http://www.exampledepot.com)
 * Ben Fry and Casey Reas. (http://www.processing.org) 
 */

public class VMDFileList {

	protected File dir;
	protected FilenameFilter filter;
	protected String[] children;
	
	public VMDFileList (String _dir) {
		
	    dir = new File(_dir);
	    filter = new FilenameFilter() {
	        public boolean accept(File dir, String name) {
	        	return name.toLowerCase().endsWith(".txt");
	        }
	    };
	    
	    children = dir.list(filter);
	    
	    if (children == null) System.out.println("VMDFileList error: no such directory");		
	}
    
    public String[] getFileList() {
    	return children;
    }	
}
