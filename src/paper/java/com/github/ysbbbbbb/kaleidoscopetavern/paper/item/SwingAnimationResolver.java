package com.github.ysbbbbbb.kaleidoscopetavern.paper.item;

import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.SwingAnimation;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Bridges Minecraft 26.2's SWING_ANIMATION and 26.3's
 * ATTACK_ANIMATION/INTERACT_ANIMATION component split.
 *
 * <p>Version-specific fields are resolved reflectively so loading the class
 * cannot throw NoSuchFieldError on the other supported server line.</p>
 */
public final class SwingAnimationResolver {
    private static final class RuntimeComponents {
        private static final ComponentNames NAMES = classify(
                Arrays.stream(DataComponentTypes.class.getFields()).map(Field::getName).toList());
        private static final DataComponentType.Valued<SwingAnimation> LEGACY = resolve("SWING_ANIMATION");
        private static final DataComponentType.Valued<SwingAnimation> ATTACK = resolve("ATTACK_ANIMATION");
        private static final DataComponentType.Valued<SwingAnimation> INTERACT = resolve("INTERACT_ANIMATION");

        private RuntimeComponents() {
        }
    }

    private SwingAnimationResolver() {
    }

    public record ComponentNames(boolean legacy, boolean split) {
    }

    /** Pure resolver used by unit tests with fake old/split field sets. */
    public static ComponentNames classify(Iterable<String> fieldNames) {
        Set<String> names = new LinkedHashSet<>();
        for (String name : fieldNames) {
            names.add(name);
        }
        return new ComponentNames(names.contains("SWING_ANIMATION"),
                names.contains("ATTACK_ANIMATION") && names.contains("INTERACT_ANIMATION"));
    }

    public static ComponentNames componentNames() {
        return RuntimeComponents.NAMES;
    }

    /** Sets the same animation on both split fields, or the legacy field. */
    public static void setUnified(ItemStack item, SwingAnimation animation) {
        if (RuntimeComponents.NAMES.split() && RuntimeComponents.ATTACK != null && RuntimeComponents.INTERACT != null) {
            item.setData(RuntimeComponents.ATTACK, animation);
            item.setData(RuntimeComponents.INTERACT, animation);
        } else if (RuntimeComponents.LEGACY != null) {
            item.setData(RuntimeComponents.LEGACY, animation);
        }
    }

    public static void unsetUnified(ItemStack item) {
        if (RuntimeComponents.NAMES.split()) {
            if (RuntimeComponents.ATTACK != null) {
                item.unsetData(RuntimeComponents.ATTACK);
            }
            if (RuntimeComponents.INTERACT != null) {
                item.unsetData(RuntimeComponents.INTERACT);
            }
        } else if (RuntimeComponents.LEGACY != null) {
            item.unsetData(RuntimeComponents.LEGACY);
        }
    }

    @SuppressWarnings("unchecked")
    private static DataComponentType.Valued<SwingAnimation> resolve(String fieldName) {
        try {
            Field field = DataComponentTypes.class.getField(fieldName);
            return (DataComponentType.Valued<SwingAnimation>) field.get(null);
        } catch (ReflectiveOperationException | ClassCastException ignored) {
            return null;
        }
    }
}
