package com.ap.autocompiler.compilation;

import com.ap.autocompiler.utils.FileUtils;
import com.ap.autocompiler.utils.Pair;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

public class BracketParser {

  private static Logger LOGGER = Logger.getLogger(
    BracketParser.class.getName()
  );

  public static List<Pair<Character, Integer>> parse(String filePath)
    throws IOException {
    List<String> lines = FileUtils.readFile(filePath);

    Stack<Character> stack = new Stack<>();
    List<Pair<Character, Integer>> brackets = new ArrayList<>();

    for (int i = 0; i < lines.size(); i++) {
      String line = lines.get(i);
      for (char c : line.toCharArray()) {
        if (c == '{' || c == '(') {
          //opening brackets are here
          stack.push(c);
          brackets.add(new Pair<Character, Integer>(c, i));
        } else {
          if (!stack.isEmpty()) {
            //last closing brackets were here
            if (c == '}' && stack.peek() == '{') {
              stack.pop();
              brackets.add(new Pair<Character, Integer>(c, i));
            } else if (c == ')' && stack.peek() == '(') {
              stack.pop();
              brackets.add(new Pair<Character, Integer>(c, i));
            }
          }
        }
      }
    }

    //remove adjecant brackets
    for (int i = 1; i < brackets.size(); i++) {
      Pair<Character, Integer> leftPair = brackets.get(i - 1);
      Pair<Character, Integer> rightPair = brackets.get(i);
      if (leftPair.getKey() == '{' && rightPair.getKey() == '}') {
        brackets.remove(i);
        brackets.remove(i - 1);
      }
      if (leftPair.getKey() == '(' && rightPair.getKey() == ')') {
        brackets.remove(i);
        brackets.remove(i - 1);
      }
    }
    LOGGER.info("brackets list updated.");
    System.out.println(brackets);

    // if (!stack.empty()) {
    //   while (!stack.isEmpty()) {
    //       errors.add(new Error(lineNumer, message))
    //   }
    // }
    return brackets;
  }
}
