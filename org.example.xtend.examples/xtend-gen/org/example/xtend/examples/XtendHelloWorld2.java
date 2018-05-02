package org.example.xtend.examples;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class XtendHelloWorld2 {
  public CharSequence generateBody(final String name, final String body) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("/* body of ");
    _builder.append(name);
    _builder.append(" */");
    _builder.newLineIfNotEmpty();
    _builder.append(body);
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence generateMethod(final String name, final String body) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("public void ");
    _builder.append(name);
    _builder.append("(){");
    _builder.newLineIfNotEmpty();
    CharSequence _generateBody = this.generateBody(name, body);
    _builder.append(_generateBody);
    _builder.newLineIfNotEmpty();
    _builder.append("}");
    _builder.newLine();
    return _builder;
  }
  
  public static void main(final String[] args) {
    final XtendHelloWorld2 generator = new XtendHelloWorld2();
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("System.out.println(\"Hello World2\");");
    _builder.newLine();
    _builder.append("return;");
    _builder.newLine();
    InputOutput.<CharSequence>println(
      generator.generateMethod(
        "m", _builder.toString()));
  }
}
