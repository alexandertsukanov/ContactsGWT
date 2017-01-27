package com.mySampleApplication.client;


import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.mySampleApplication.entity.Contacts;

import java.util.List;

@RemoteServiceRelativePath("MySampleApplicationService")
public interface MySampleApplicationService extends RemoteService {


    List<Contacts> getAllUsers();

    Contacts saveUser(Contacts user);

    Contacts deleteUser(Contacts user);

    Contacts updateUser(Contacts user);

}
