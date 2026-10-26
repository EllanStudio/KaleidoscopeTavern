package com.github.ysbbbbbb.kaleidoscopetavern.paper.api.event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * 玩家喝下一份酒馆饮品后触发（果汁桶与牛奶不算）。
 * 仅用于通知其他插件（如成就），不可取消。
 */
public class TavernDrinkEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final ItemStack drink;

    public TavernDrinkEvent(Player who, ItemStack drink) {
        super(who);
        this.drink = drink.clone();
    }

    public ItemStack drink() {
        return drink.clone();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
