package com.ednaldoluiz.moviedash;

import com.ednaldoluiz.moviedash.service.CacheTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        MoviedashApplicationTests.class,
        CacheTests.class
})
public class SuiteTests {
}
