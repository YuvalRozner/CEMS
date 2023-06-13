package controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import JDBC.Msg;
import JDBC.MsgType;
import enteties.CemsFile;
import enteties.User;


public class CemsFileController {
	/**
	 * make path file to File.
	 * @param message path of the file.
	 */
	  public Msg createMsgWithFile(String message)
	  {  
		  Msg msgToServer= new Msg(MsgType.file);
		  String LocalfilePath=message;
		  //diagnosisE.jpg
			
		  try{
			      File newFile = new File (LocalfilePath);
			      String name = newFile.getName();   
			      CemsFile msg = new CemsFile(name);
			      byte [] mybytearray  = new byte [(int)newFile.length()];
			      FileInputStream fis = new FileInputStream(newFile);
			      BufferedInputStream bis = new BufferedInputStream(fis);			  
			      
			      msg.initArray(mybytearray.length);
			      msg.setSize(mybytearray.length);
			      
			      bis.read(msg.getMybytearray(),0,mybytearray.length);
			      msgToServer.setCemsFile(msg);
		      
			    }
			catch (Exception e) {
				System.out.println("Error send (Files)msg) to Server");
			}
		  return msgToServer;
	  }
	  /**
	   * take the file in the massage put in the data in the original file and download it in the right location.
	   * @param msg
	   */
	  public void saveFile(Msg msg){
		  CemsFile file=msg.getCemsFile();
		  System.out.println("file is "+file);
		  int fileSize =file.getSize(); 
			System.out.println("length "+ fileSize);
			File myfile =new File("Recived_"+file.getFileName());
			try {
				FileOutputStream fos = new FileOutputStream(myfile);
				BufferedOutputStream bos =new BufferedOutputStream(fos);
				try {
					bos.write(file.getMybytearray(), 0, fileSize);
					bos.flush();
					fos.flush();
				} catch (IOException e) {
					System.out.println("upload faild");
				}
				
			} catch (FileNotFoundException e) {
				System.out.println("upload faild");
			}
	  }

}
