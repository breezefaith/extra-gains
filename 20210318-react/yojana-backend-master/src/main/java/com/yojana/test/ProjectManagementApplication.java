/**
 *
 */
package com.yojana.test;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yogeshverma
 *
 */
@ApplicationPath("/api")
public class ProjectManagementApplication extends Application {
    private final Set<Object> singletons = new HashSet<Object>();

    public ProjectManagementApplication() {
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
