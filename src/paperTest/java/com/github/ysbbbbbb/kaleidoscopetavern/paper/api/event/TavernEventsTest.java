package com.github.ysbbbbbb.kaleidoscopetavern.paper.api.event;

import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TavernEventsTest {
    private static final List<Class<? extends PlayerEvent>> EVENTS = List.of(
            TavernPressEvent.class, TavernShakeEvent.class, TavernBarrelTapEvent.class, TavernDrinkEvent.class);

    @Test
    void eventsAreInformationalPlayerEvents() throws Exception {
        for (Class<? extends PlayerEvent> type : EVENTS) {
            assertTrue(Modifier.isPublic(type.getModifiers()), type.getSimpleName());
            assertFalse(Cancellable.class.isAssignableFrom(type), type.getSimpleName() + " must not be cancellable");
            Object handlers = type.getMethod("getHandlerList").invoke(null);
            assertNotNull(handlers, type.getSimpleName());
        }
    }

    @Test
    void productionEventsExposeRecipeAndProduct() throws Exception {
        for (Class<? extends PlayerEvent> type : List.of(
                TavernPressEvent.class, TavernShakeEvent.class, TavernBarrelTapEvent.class)) {
            assertEquals(String.class, type.getMethod("recipeId").getReturnType(), type.getSimpleName());
            assertEquals(org.bukkit.inventory.ItemStack.class, type.getMethod("product").getReturnType(), type.getSimpleName());
            assertEquals(org.bukkit.Location.class, type.getMethod("location").getReturnType(), type.getSimpleName());
        }
        assertEquals(org.bukkit.inventory.ItemStack.class, TavernDrinkEvent.class.getMethod("drink").getReturnType());
    }

    @Test
    void eachEventHasItsOwnHandlerList() throws Exception {
        Object press = TavernPressEvent.class.getMethod("getHandlerList").invoke(null);
        assertSame(press, TavernPressEvent.class.getMethod("getHandlerList").invoke(null));
        assertFalse(press == TavernShakeEvent.class.getMethod("getHandlerList").invoke(null));
    }
}
