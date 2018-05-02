package org.example.xtend.examples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class testing_lambdas {
  public static void main(final String[] args) {
    final ArrayList<String> list = CollectionLiterals.<String>newArrayList("second", "first", "third", "vier", "zzzz");
    InputOutput.<ArrayList<String>>println(list);
    final Comparator<String> _function = (String $0, String $1) -> {
      return $0.compareToIgnoreCase($1);
    };
    Collections.<String>sort(list, _function);
    InputOutput.<ArrayList<String>>println(list);
  }
}
