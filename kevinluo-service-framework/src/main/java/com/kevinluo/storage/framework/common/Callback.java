package com.kevinluo.storage.framework.common;

/*
 * Creates on 2020/5/23.
 */

/**
 * @author lts
 */
public interface Callback {
  <T> T apply(T o);
}