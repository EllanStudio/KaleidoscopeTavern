package com.github.ysbbbbbb.kaleidoscopetavern.paper.item;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class SwingAnimationResolverTest {
    @Test
    void detectsLegacyAndSplitComponentLayouts() {
        SwingAnimationResolver.ComponentNames legacy =
                SwingAnimationResolver.classify(Set.of("SWING_ANIMATION"));
        assertTrue(legacy.legacy());
        assertFalse(legacy.split());

        SwingAnimationResolver.ComponentNames split =
                SwingAnimationResolver.classify(Set.of("ATTACK_ANIMATION", "INTERACT_ANIMATION"));
        assertFalse(split.legacy());
        assertTrue(split.split());

        SwingAnimationResolver.ComponentNames partial =
                SwingAnimationResolver.classify(Set.of("ATTACK_ANIMATION"));
        assertFalse(partial.split());
    }
}
