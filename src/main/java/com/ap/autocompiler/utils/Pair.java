package com.ap.autocompiler.utils;

public class Pair<K, V> {

  K key;
  V value;

  public Pair(K key, V value) {
    this.key = key;
    this.value = value;
  }

  public K getKey() {
    return this.key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  public V getValue() {
    return this.value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return (
      "{" + " key='" + getKey() + "'" + ", value='" + getValue() + "'" + "}"
    );
  }
}
