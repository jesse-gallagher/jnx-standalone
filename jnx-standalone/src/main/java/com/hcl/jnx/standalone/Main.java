package com.hcl.jnx.standalone;

import com.hcl.domino.DominoClient;
import com.hcl.domino.DominoClientBuilder;
import com.hcl.domino.DominoProcess;
import com.hcl.domino.DominoProcess.DominoThreadContext;

public class Main {

  public static void main(String[] args) {
    System.out.println("hello");
    
    DominoProcess.get().initializeProcess(args);
    try(DominoThreadContext threadContext = DominoProcess.get().initializeThread()) {
      try(DominoClient client = DominoClientBuilder.newDominoClient().build()) {
        System.out.println("I am " + client.getEffectiveUserName());
      }
    } finally {
      DominoProcess.get().terminateProcess();
    }
  }

}
