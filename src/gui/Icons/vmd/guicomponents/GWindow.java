/*
  Part of the GUI for Processing library 
  	http://gui4processing.lagers.org.uk
	http://gui4processing.googlecode.com/svn/trunk/

  Copyright (c) 2008-09 Peter Lager

  This library is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  This library is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General
  Public License along with this library; if not, write to the
  Free Software Foundation, Inc., 59 Temple Place, Suite 330,
  Boston, MA  02111-1307  USA
 */

package gui.Icons.vmd.guicomponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;

import javax.swing.table.AbstractTableModel;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Objects of this class are separate windows which can be used to hold
 * G4P GUI components or used for drawing or both combined.
 * <br><br>
 * A number of examples are included in the library and can be found
 * at gui4processing.lagers.org.uk
 * 
 * 
 * @author Peter Lager
 *
 */
@SuppressWarnings("serial")
public class GWindow extends Frame implements GConstants {

	protected PApplet app;
	public GWinApplet embed;

	protected String winName;

	public GWinData data;
	
	protected int exitBehaviour = CLOSE_ON_EXIT;
	
	
	/**
	 * Remember what we have registered for.
	 */
	protected boolean regDraw = false;
	protected boolean regMouse = false;
	protected boolean regPre = false;
	protected boolean regKey = false;
	protected boolean regPost = false;

	/** The object to handle the pre event */
	protected Object preHandlerObject = null;
	/** The method in preHandlerObject to execute */
	protected Method preHandlerMethod = null;
	/** the name of the method to handle the event */ 
	protected String preHandlerMethodName;

	/** The object to handle the draw event */
	protected Object drawHandlerObject = null;
	/** The method in drawHandlerObject to execute */
	protected Method drawHandlerMethod = null;
	/** the name of the method to handle the event */ 
	protected String drawHandlerMethodName;

	/** The object to handle the mouse event */
	protected Object mouseHandlerObject = null;
	/** The method in mouseHandlerObject to execute */
	protected Method mouseHandlerMethod = null;
	/** the name of the method to handle the event */ 
	protected String mouseHandlerMethodName;

	/** The object to handle the post event */
	protected Object postHandlerObject = null;
	/** The method in postHandlerObject to execute */
	protected Method postHandlerMethod = null;
	/** the name of the method to handle the event */ 
	protected String postHandlerMethodName;

        AbstractTableModel dataInVmd;

	/**
	 * Create a window that can be used to hold G4P components or used
	 * for drawing or both together.
	 * 
	 * @param theApplet
	 * @param name
	 * @param x initial position on the screen
	 * @param y initial position on the screen
	 * @param w width of the drawing area (the frame will be bigger to accommodate border)
	 * @param h height of the drawing area (the frame will be bigger to accommodate border and title bar)
	 * @param background background color to use
	 */
	public GWindow(PApplet theApplet, String name, int x, int y, int w, int h, boolean noFrame, String mode, AbstractTableModel dataIn) {
		super(name);
		app = theApplet;
		winName = name;
                dataInVmd = dataIn;

		if(mode == null || mode.equals(""))
			mode = PApplet.JAVA2D;
		
		embed = new GWinApplet(mode);
		embed.owner = this;
		embed.frame = this;
		embed.frame.setResizable(true);

		embed.appWidth = w;
		embed.appHeight = h;

		windowCtorCore(x, y, w, h, noFrame, mode);
		
		super.setResizable(true);
	}

	/**
	 * 
	 * @param theApplet
	 * @param name
	 * @param x initial position on the screen
	 * @param y initial position on the screen
	 * @param image background image (used to size window)
	 * @param noFrame if true then the frame has no border
	 * @param mode JAVA2D / P2D / P3D / OPENGL
	 */
	public GWindow(PApplet theApplet, String name, int x, int y, PImage image, boolean noFrame, String mode) {
		super(name);
		app = theApplet;
		winName = name;
				
		if(mode == null || mode.equals(""))
			mode = PApplet.JAVA2D;
		
		embed = new GWinApplet(mode);
		embed.owner = this;
		embed.frame = this;
		embed.frame.setResizable(true);
		/// Get image details to set size
		embed.bkImage = image;
		embed.appWidth = image.width;
		embed.appHeight = image.height;

		windowCtorCore(x, y, image.width, image.height, noFrame, mode);
		
		super.setResizable(false);
	}

	/**
	 * Core stuff for GWindows ctor
	 * 
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param noFrame
	 * @param mode
	 */
	private void windowCtorCore(int x, int y, int w, int h, boolean noFrame, String mode){
		embed.bkColor = embed.color(0);
		
		embed.resize(embed.appWidth, embed.appHeight);
		embed.setPreferredSize(new Dimension(embed.appWidth, embed.appHeight));
		embed.setMinimumSize(new Dimension(embed.appWidth, embed.appHeight));

		// add the PApplet to the Frame
		setLayout(new BorderLayout());
		add(embed, BorderLayout.CENTER);

		// ensures that the animation thread is started and
		// that other internal variables are properly set.
		embed.init();

		// add an exit on close listener
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				switch(exitBehaviour){
				case CLOSE_ON_EXIT:
					// Don't remove the control
					//removeFromG4P();
					// close this frame
					dispose();
					break;
				case SHUTDOWN_ON_EXIT:
					System.exit(0);
					break;
					}
			}
		});
		// Pack the window, position it and make visible
		setUndecorated(noFrame);
		pack();
		setLocation(x,y);
		setVisible(true);
		
		// At least get a blank screen
		embed.registerDraw(embed);
		regDraw = true;
		
		// Make the window always on top
		setOnTop(true);
		
		// Make sure we have some data even if not used
		String filePath = "fisher.iris.txt"; ///banderas.txt"; //datos_LPCI_fin.dos.txt"); //Estandarizaci�n.txt");
		data = new GWinData(dataInVmd);
		data.owner = this;
		
		// Make sure G4P knows about this window
		G4P.addControlWindow(this);
	}
	
	/**
	 * Add a G4P component onto the window.
	 * 
	 * @param component
	 */
	public void add(GComponent component){
		component.changeWindow(embed);
	}

	/**
	 * Add an object that holds the data this window needs to use.
	 * 
	 * Note: the object can be of any class that extends GWinData.
	 * 
	 * @param data
	 */
	public void addData(GWinData data){
		this.data = data;
		this.data.owner = this;
	}
	
	/**
	 * Always make this window appear on top of other windows (or not). <br>
	 * This will not work when run from a remote server (ie Applet over the web)
	 * for security reasons. In this situation a call to this method is ignored
	 * and no error is generated. 
	 * 
	 * @param onTop
	 */
	public void setOnTop(boolean onTop){
		try{
			setAlwaysOnTop(onTop);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the location of the window. <br>
	 * (Already available from the Frame class - helps visibility 
	 * of method in G4P reference)
	 */
	public void setLocation(int x, int y){
		super.setLocation(x,y);
	}
	
	/**
	 * Sets the visibility of the window <br>
	 * (Already available from the Frame class - helps visibility 
	 * of method in G4P reference)
	 */
	public void setVisible(boolean visible){
		super.setVisible(visible);
	}
	
	/**
	 * Determines whether the window is resizabale or not. <br>
	 * This cannot be set to true if a background image is used.
	 */
	public void setResizable(boolean resizable){
		if(resizable == false)
			super.setResizable(false);
		else {
			if(embed.bkImage == null)
				super.setResizable(true);
		}
	}
	
	/**
	 * Set the background image to be used instead of a plain color background <br>
	 * The window will resize to accommodate the image.
	 * @param image
	 */
	public void setBackground(PImage image){
		embed.noLoop();
		embed.bkImage = null;
		super.setResizable(true);
		embed.resize(image.width, image.height);
		embed.bkImage = image;
		embed.appWidth = image.width;
		embed.appHeight = image.height;
		embed.setPreferredSize(new Dimension(embed.appWidth, embed.appHeight));
		embed.setMinimumSize(new Dimension(embed.appWidth, embed.appHeight));
		pack();
		super.setResizable(false);
		embed.loop();
	}
	
	/**
	 * Set the background color for the window.
	 * 
	 * @param col
	 */
	public void setBackground(int col){
		embed.bkColor = col;
	}

	/**
	 * Determines what happens when the Frame is closed by the user.
	 * <br>
	 * GWindow.CLOSE_ON_EXIT  - closes/hides the window <br>
	 * GWindow.SHUTDOWN_ON_EXIT  - ends the application if the window is closed
	 * 
	 * @param exitBehaviour the exitBehaviour to set
	 */
	public void setExitBehaviour(int exitBehaviour) {
		this.exitBehaviour = exitBehaviour;
	}

	/**
	 * @see setExitBehaviour
	 * @return the exitBehaviour
	 */
	public int getExitBehaviour() {
		return exitBehaviour;
	}

	/**
	 * Used to remove from G4P when the Frame is disposed.
	 */
	private void removeFromG4P(){
		embed.noLoop();
		embed.unregisterPost(embed);
		if(regDraw)
			embed.unregisterDraw(embed);
		if(regPre)
			embed.unregisterPre(embed);
		if(regMouse)
			embed.unregisterMouseEvent(embed);
		regDraw = regPre = regMouse = false;
		G4P.removeControlWindow(this);
	}

	/**
	 * Attempt to add the 'draw' handler method. 
	 * The default event handler is a method that returns void and has two
	 * parameters Papplet and GWinData
	 * 
	 * @param obj the object to handle the event
	 * @param methodName the method to execute in the object handler class
	 */
	public void addDrawHandler(Object obj, String methodName){
		try{
			drawHandlerMethod = obj.getClass().getMethod(methodName, new Class[] {GWinApplet.class, GWinData.class } );
			drawHandlerObject = obj;
			drawHandlerMethodName = methodName;
		} catch (Exception e) {
			GMessenger.message(NONEXISTANT, this, new Object[] {methodName, new Class[] { this.getClass() } } );
		}
	}

	/**
	 * Attempt to add the 'pre' handler method. 
	 * The default event handler is a method that returns void and has two
	 * parameters Papplet and GWinData
	 * 
	 * @param obj the object to handle the event
	 * @param methodName the method to execute in the object handler class
	 */
	public void addPreHandler(Object obj, String methodName){
		try{
			preHandlerMethod = obj.getClass().getMethod(methodName, new Class[] {GWinApplet.class, GWinData.class } );
			preHandlerObject = obj;
			preHandlerMethodName = methodName;
			embed.registerPre(embed);
			regPre = true;
		} catch (Exception e) {
			GMessenger.message(NONEXISTANT, this, new Object[] {methodName, new Class[] { this.getClass() } } );
		}
	}

	/**
	 * Attempt to add the 'mouse' handler method. 
	 * The default event handler is a method that returns void and has two
	 * parameters Papplet and GWinData
	 * 
	 * @param obj the object to handle the event
	 * @param methodName the method to execute in the object handler class
	 */
	public void addMouseHandler(Object obj, String methodName){
		try{
			mouseHandlerMethod = obj.getClass().getMethod(methodName, 
					new Class[] {GWinApplet.class, GWinData.class, MouseEvent.class } );
			mouseHandlerObject = obj;
			mouseHandlerMethodName = methodName;
			embed.registerMouseEvent(embed);
			regMouse = true;
		} catch (Exception e) {
			GMessenger.message(NONEXISTANT, this, new Object[] {methodName, new Class[] { this.getClass() } } );
		}
	}

	/**
	 * Attempt to add the 'mouse' handler method. 
	 * The default event handler is a method that returns void and has two
	 * parameters Papplet and GWinData
	 * 
	 * @param obj the object to handle the event
	 * @param methodName the method to execute in the object handler class
	 */
	public void addPostHandler(Object obj, String methodName){
		try{
			postHandlerMethod = obj.getClass().getMethod(methodName, 
					new Class[] {GWinApplet.class, GWinData.class } );
			postHandlerObject = obj;
			postHandlerMethodName = methodName;
			regPost = true;
		} catch (Exception e) {
			GMessenger.message(NONEXISTANT, this, new Object[] {methodName, new Class[] { this.getClass() } } );
		}
	}

}
