package com.github.ysbbbbbb.kaleidoscopetavern.paper.api.event;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * 玩家用空桶从压榨桶中成功取出果汁后触发。
 * 仅用于通知其他插件（如成就），不可取消。
 */
public class TavernPressEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Location location;
    private final String recipeId;
    private final ItemStack product;

    public TavernPressEvent(Player who, Location location, String recipeId, ItemStack product) {
        super(who);
        this.location = location.clone();
        this.recipeId = recipeId;
        this.product = product.clone();
    }

    public Location location() {
        return location.clone();
    }

    /** 配方 id；无配方匹配的特殊产物为其物品 id。 */
    public String recipeId() {
        return recipeId;
    }

    public ItemStack product() {
        return product.clone();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
