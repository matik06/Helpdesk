/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.managed.bean.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author matik06
 */
@ManagedBean
@ViewScoped
public class Bean implements Serializable {

    private List<Item> items;
    private Item item;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
        items.add(new Item(1L, "one"));
        items.add(new Item(2L, "two"));
        items.add(new Item(3L, "three"));
        
        item = new Item(0L, "zero");
        System.out.println("post construct finished");
    }

    public void submit() {
        System.out.println(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public void process() {
        System.out.println("selected item: ");
        System.out.println(item);
    }
}