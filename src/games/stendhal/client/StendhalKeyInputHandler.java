package games.stendhal.client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

import marauroa.common.*;
import marauroa.common.game.*;

/** Handles key inputs for ingame events */
public class StendhalKeyInputHandler extends KeyAdapter 
  {
  private Map<Integer, Object> pressed;
  private StendhalClient client;
  private boolean pressedESC;
  
  public StendhalKeyInputHandler(StendhalClient client)
    {
    super();
    this.client=client;
    pressed=new HashMap<Integer,Object>();
    }
    
  public boolean isExitRequested()
    {
    return pressedESC;
    }
     
  public void onKeyPressed(KeyEvent e)  
    {
    RPAction action;
    
    if(e.getKeyCode()==KeyEvent.VK_LEFT && e.isControlDown())
      {
      action=new RPAction();
      action.put("type","face");
      action.put("dir",0);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_RIGHT && e.isControlDown())
      {
      action=new RPAction();
      action.put("type","face");
      action.put("dir",1);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_UP && e.isControlDown())
      {
      action=new RPAction();
      action.put("type","face");
      action.put("dir",2);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_DOWN && e.isControlDown())
      {
      action=new RPAction();
      action.put("type","face");
      action.put("dir",3);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_LEFT)
      {
      action=new RPAction();
      action.put("type","move");
      action.put("dx",-0.8);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_RIGHT)
      {
      action=new RPAction();
      action.put("type","move");
      action.put("dx",0.8);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_UP)
      {
      action=new RPAction();
      action.put("type","move");
      action.put("dy",-0.8);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_DOWN)
      {
      action=new RPAction();
      action.put("type","move");
      action.put("dy",0.8);
      client.send(action);
      }
    else if(e.getKeyCode()==KeyEvent.VK_ENTER && e.isControlDown())
      {
      action=new RPAction();
      action.put("type","change");
      action.put("dest","city");
      client.send(action);
      }      
    }
    
  public void onKeyReleased(KeyEvent e)  
    {
    RPAction action=new RPAction();
    action.put("type","move");
    
    switch(e.getKeyCode())
      {
      case KeyEvent.VK_LEFT:
        action.put("dx",0);
        break;
      case KeyEvent.VK_RIGHT:
        action.put("dx",0);
        break;
      case KeyEvent.VK_UP:
        action.put("dy",0);
        break;
      case KeyEvent.VK_DOWN:      
        action.put("dy",0);
        break;
      }

    if(action.has("dx") || action.has("dy"))
      {
      Logger.trace("StendhalKeyInputHandler::onKeyReleased","D","Sending action "+action);
      client.send(action);
      }    
    }
    
  public void keyPressed(KeyEvent e) 
    {
    if(!pressed.containsKey(new Integer(e.getKeyCode())))
      {
      onKeyPressed(e);
      pressed.put(new Integer(e.getKeyCode()),null);
      }      
    }
      
  public void keyReleased(KeyEvent e) 
    {
    onKeyReleased(e);
    pressed.remove(new Integer(e.getKeyCode()));
    }

  public void keyTyped(KeyEvent e) 
    {
    // if we hit escape, then quit the game
    if (e.getKeyChar() == 27) 
      {
      client.requestLogout();
      }
    }
  }
