/**
 * ISysNotifyTodoWebService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.utils.aowsdl;

public interface ISysNotifyTodoWebService extends java.rmi.Remote {
    public com.utils.aowsdl.NotifyTodoAppResult updateTodo(com.utils.aowsdl.NotifyTodoUpdateContext arg0) throws java.rmi.RemoteException, com.utils.aowsdl.Exception;
    public com.utils.aowsdl.NotifyTodoAppResult setTodoDone(com.utils.aowsdl.NotifyTodoRemoveContext arg0) throws java.rmi.RemoteException, com.utils.aowsdl.Exception;
    public com.utils.aowsdl.NotifyTodoAppResult getTodo(com.utils.aowsdl.NotifyTodoGetContext arg0) throws java.rmi.RemoteException, com.utils.aowsdl.Exception;
    public com.utils.aowsdl.NotifyTodoAppResult getTodoCount(com.utils.aowsdl.NotifyTodoGetCountContext arg0) throws java.rmi.RemoteException, com.utils.aowsdl.Exception;
    public com.utils.aowsdl.NotifyTodoAppResult deleteTodo(com.utils.aowsdl.NotifyTodoRemoveContext arg0) throws java.rmi.RemoteException, com.utils.aowsdl.Exception;
    public com.utils.aowsdl.NotifyTodoAppResult sendTodo(com.utils.aowsdl.NotifyTodoSendContext arg0) throws java.rmi.RemoteException, com.utils.aowsdl.Exception;
}
