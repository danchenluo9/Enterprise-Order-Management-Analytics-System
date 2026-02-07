/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FinalProject;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danchenluo
 */
public class ChannelDirectory {
    private List<Channel> channels = new ArrayList<>();
    
    public List<Channel> getChannels() {
        return channels;
    }
    
    public void addChannel(Channel ch) {
        channels.add(ch);
    }

}
