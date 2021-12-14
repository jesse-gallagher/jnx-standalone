package com.hcl.jnx.standalone;

import com.hcl.domino.DominoClient;
import com.hcl.domino.DominoClientBuilder;
import com.hcl.domino.DominoProcess;
import com.hcl.domino.DominoProcess.DominoThreadContext;
import com.hcl.domino.data.Database;
import com.hcl.domino.design.DbDesign;
import com.hcl.domino.jnx.vertx.json.service.VertxJsonSerializer;
import com.hcl.domino.json.JsonSerializer;

public class Main {

  public static void main(String[] args) {
    System.out.println("hello");
    
    DominoProcess.get().initializeProcess(args);
    try(DominoThreadContext threadContext = DominoProcess.get().initializeThread()) {
      try(DominoClient client = DominoClientBuilder.newDominoClient().build()) {
        System.out.println("I am " + client.getEffectiveUserName());
        
        JsonSerializer serializer = new VertxJsonSerializer();
        
        Database names = client.openDatabase("names.nsf");
        DbDesign design = names.getDesign();
        System.out.println(serializer.toJson(design.getForm("Person").get()));
        
      }
    } finally {
      DominoProcess.get().terminateProcess();
    }
  }

}
