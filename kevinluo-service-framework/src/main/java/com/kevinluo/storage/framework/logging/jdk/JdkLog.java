package com.kevinluo.storage.framework.logging.jdk;

import com.kevinluo.storage.framework.logging.Log;
import com.kevinluo.storage.framework.utils.StringUtils;

import java.util.logging.Level;

/*
 * creates on 2020/5/11 20:52.
 */

/**
 * @author lts
 */
public class
JdkLog implements Log,java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private final java.util.logging.Logger log;

	public JdkLog(java.util.logging.Logger log) {
		this.log = log;
	}

	@Override
	public boolean isBad() {
		return false;
	}

	@Override
	public boolean isDebugEnable() {
		return log.isLoggable(Level.FINE);
	}

	@Override
	public void info(Object msg) {
		log.log(Level.INFO,String.valueOf(msg));
	}

	@Override
	public void warn(Object msg) {
		log.log(Level.WARNING,String.valueOf(msg));
	}

	@Override
	public void error(Object msg) {
		log.log(Level.SEVERE,String.valueOf(msg));

	}

	@Override
	public void debug(Object msg) {
		log.log(Level.FINE,String.valueOf(msg));
	}

	@Override
	public void info(Object msg, Object... formats) {
		String msg1 = String.valueOf(msg);
		msg1 = StringUtils.format(msg1, formats);
		log.log(Level.INFO,msg1);
	}

	@Override
	public void warn(Object msg, Object... formats) {
		String msg1 = String.valueOf(msg);
		msg1 = StringUtils.format(msg1, formats);
		log.log(Level.WARNING,msg1);
	}

	@Override
	public void error(Object msg, Object... formats) {
		String msg1 = String.valueOf(msg);
		msg1 = StringUtils.format(msg1, formats);
		log.log(Level.SEVERE,msg1);
	}

	@Override
	public void debug(Object msg, Object... formats) {
		String msg1 = String.valueOf(msg);
		msg1 = StringUtils.format(msg1, formats);
		log.log(Level.FINE,msg1);
	}

	@Override
	public void error(Object msg, Throwable e) {
		log.log(Level.SEVERE,String.valueOf(msg), e);
	}

	@Override
	public void error(Object msg, Throwable e, StackTraceElement element)
	{
		error(msg, e);
	}

	@Override
	public void error(Object msg, Throwable e, Object... formats) {
		String msg1 = String.valueOf(msg);
		msg1 = StringUtils.format(msg1, formats);
		log.log(Level.SEVERE,msg1);
	}
}
