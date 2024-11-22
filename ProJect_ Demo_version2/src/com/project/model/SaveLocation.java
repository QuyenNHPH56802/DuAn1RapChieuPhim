/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.project.model;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author admin
 */
public class SaveLocation {
    public static String chooseSave(){
        JFileChooser filejchoose=  new JFileChooser();
        filejchoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Chỉ cho phép chọn thư mục
        int result = filejchoose.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File selected = filejchoose.getSelectedFile();
            return selected.getAbsolutePath();
        }
        return null;
    }
}
