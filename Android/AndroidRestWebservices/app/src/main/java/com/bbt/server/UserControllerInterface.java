package com.bbt.server;

import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.Delete;

import com.bbt.model.Container;
import com.bbt.model.User;

public interface UserControllerInterface {
    @Put
    public void create(User user);

    @Get
    public Container getAllUsers();

    @Delete
    public void remove();
}
