package com.udPlatform;

import java.io.Serializable;

public interface CRUD<T extends Entity> extends Serializable{
    void create(T parent);
    void update(T parent);
    void delete(T parent);
}
