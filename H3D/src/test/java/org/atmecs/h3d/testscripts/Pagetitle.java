package org.atmecs.h3d.testscripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Pagetitle {
	    public static void main(String[] args) {
	        String sentence = "This is a sample sentence with some odd words";
	        
	        String[] words = sentence.split(" ");
	        
	        for (int i = 0; i < words.length; i++) {
	            if (i % 2 != 0) { // Check if the word is at an odd index
	                String reversedWord = reverseWord(words[i]);
	                words[i] = reversedWord;
	            }
	        }
	        
	        String result = String.join(" ", words);
	        System.out.println(result);
	    }
	    
	    public static String reverseWord(String word) {
	        char[] charArray = word.toCharArray();
	        int start = 0;
	        int end = charArray.length - 1;
	        
	        while (start < end) {
	            char temp = charArray[start];
	            charArray[start] = charArray[end];
	            charArray[end] = temp;
	            
	            start++;
	            end--;
	        }
	        
	        return new String(charArray);
	    }
	}


