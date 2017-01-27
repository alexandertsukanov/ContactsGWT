package com.mySampleApplication.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mySampleApplication.entity.Contacts;

import java.util.List;

@RemoteServiceRelativePath("MySampleApplicationService")
public interface MySampleApplicationService extends RemoteService {
    // Sample interface method of remote interface

    public List<Contacts> getAllUsers();

    public Contacts saveUser(Contacts user);

    public Contacts deleteUser(Contacts user);

    public Contacts updateUser(Contacts user);

}
