package org.cantaloupe.junit;

import java.util.List;

import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.notification.RunNotifier;
import org.junit.runner.notification.StoppedByUserException;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class CantaloupeJUnitRunner extends BlockJUnit4ClassRunner {

	public CantaloupeJUnitRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
	}

	/*
	 * 1. read system envs :-use send - per url 2. create thread local: -clear
	 * before every test -persist jsons in files -collect links in threadlocal
	 */
}
