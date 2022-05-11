//HAWAIIAN WORD

//makeWord(String s) --> creates the response listed on the documentation

import java.util.Scanner;
/*
humuhumunukunukuapua'a -  Hoo-moo-hoo-moo-noo-koo-noo-koo-ah-poo-ah'ah
E komo mai - Eh koh-moh meye

*/
class Main {
  public static void main(String[] args) {
    askForWord();
 // System.out.println(removeDash("Eh- koh-moh- meye-ah"));
  }

  public static void askForWord() {
    Scanner sc = new Scanner(System.in);
    String input;
    String option;
    do {
      do {
        System.out.print("Enter a hawaiian word to pronounce: ");
        input = sc.nextLine();
        input = input.toLowerCase();
      } while (!isInvalid(input));
      System.out.println(input.toUpperCase() + " is pronounced " + removeDash(makeWord(input)));

      {
        System.out.print("Do you want to enter another word? Y/YES/N/NO ==> ");
        option = sc.nextLine().toLowerCase();
        if (!option.equals("y") && !option.equals("yes") && !option.equals("n") && !option.equals("no")) {
          System.out.println("Enter Y, YES, N or NO");
        }
      } while (!option.equals("y") && !option.equals("yes") && !option.equals("n") && !option.equals("no"));

    } while (option.equals("y") || option.equals("yes"));
  }

  public static boolean isInvalid(String s) {
    String valid = "aeioupkhlmn'w  ";
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (valid.indexOf(c) == -1) {
        return false;
      }
    }
    return true;
  }

  public static boolean isVowel(String s) {
    String[] vowels = { "a", "e", "i", "o", "u" };
    for (int i = 0; i < vowels.length; i++) {
      if (s.equals(vowels[i])) {
        return true;
      }
    }
    return false;
  }

  public static boolean isConsonant(String s) { // same as isVowel(h, k, l, m, n, p, w, and ')
    String[] consonants = { "h", "k", "l", "m", "n", "p", "w" };
    for (int i = 0; i < consonants.length; i++) {
      if (s.equals(consonants[i])) {
        return true;
      }
    }
    return false;
  }

  public static boolean isDoubleVowel(String s) { // same as isVowel
    String[] DoubleVowel = { "ai", "ae", "ao", "au", "ei", "eu", "iu", "oi", "ou", "ui" };
    for (int i = 0; i < DoubleVowel.length; i++) {
      if (s.equals(DoubleVowel[i])) {
        return true;
      }
    }
    return false;
  }

  public static String getVowel(String s) {
    String[] vowels = { "a", "e", "i", "o", "u" };
    String[] translations = { "ah-", "eh-", "ee-", "oh-", "oo-" };
    int index = 0;
    for (int i = 0; i < vowels.length; i++) {
      if (s.equals(vowels[i])) {
        index = i;
        continue;
      }
    }
    return translations[index];

  }

  public static String getDoubleVowel(String c) { // same as vowel
    String[] doublevowel = { "ai", "ae", "ao", "au", "ei", "eu", "iu", "oi", "ou", "ui" };
    String[] translations = { "eye-", "eye-", "ow-", "ay-", "eh-oo-", "ew-", "oy-", "ow-", "ooey-" };
    int index = 0;
    for (int i = 0; i < doublevowel.length; i++) {
      if (c.equals(doublevowel[i])) {
        index = i;
      }
    }
    return translations[index];
  }

  public static String getW(String s) { // take in w and letter before
    if (s.substring(0, 1).equals("i") || s.substring(0, 1).equals("e"))
      return "w";
    else
      return "v";
  }

  public static String removeDash(String s) {
   // System.out.println("OG: "+s);
    for (int i = 0;i<s.length()-1; i++){
      String curr = s.substring(i,i+1);
      if (curr.equals("-")) {
        if (s.substring(i+1,i+2).equals(" ")||s.substring(i+1,i+2).equals("'")) {
          s = s.substring(0,i)+s.substring(i+1);
        }
      }
    }
    if (s.substring(s.length() - 1, s.length()).equals("-")) {
      s = s.substring(0, s.length() - 1);
      }
    return s;
 
  }

  public static String makeWord(String s) {
    String newString = "";
    s = s.toLowerCase();
    if (s.equals("invalid")) { 
      return "invalid";
    }
    for (int i = 0; i < s.length(); i++) {
      String currentLetter = s.substring(i, i + 1);
     // System.out.println("Curr: "+currentLetter);
      if (i != s.length() - 1 && isDoubleVowel(s.substring(i, i + 2))) {
        String doublevowel = s.substring(i, i + 2);
        newString += getDoubleVowel(doublevowel);
        i++;
      }
      else if (isVowel(currentLetter)) {
        newString += getVowel(currentLetter);
      //  System.out.println("adding: "+getVowel(currentLetter));
      } else if (i != 0 && currentLetter == "w") {
        newString += getW(s.substring(i - 1, i + 1));
      } else  {
        newString += currentLetter;
       // System.out.println("Adding: "+currentLetter);
      }

    } 
    //newString = removeDash(newString);
    newString = newString.substring(0,1).toUpperCase() + newString.substring(1);
    String temp = s.toUpperCase()+" is pronounced "+removeDash(newString);
    return temp;
  }
}
