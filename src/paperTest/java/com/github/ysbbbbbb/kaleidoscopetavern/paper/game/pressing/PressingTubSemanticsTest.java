package com.github.ysbbbbbb.kaleidoscopetavern.paper.game.pressing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PressingTubSemanticsTest {
    @Test
    void sneakingWithAnotherTubRequestsPlacementDelegation() {
        assertTrue(PressingTubSemantics.shouldDelegateTubPlacement(
                true, "kaleidoscope_tavern:pressing_tub"));
    }

    @Test
    void ordinaryTubUseAndOtherHeldItemsRemainContainerInteractions() {
        assertFalse(PressingTubSemantics.shouldDelegateTubPlacement(
                false, "kaleidoscope_tavern:pressing_tub"));
        assertFalse(PressingTubSemantics.shouldDelegateTubPlacement(
                true, "kaleidoscope_tavern:grape"));
        assertFalse(PressingTubSemantics.shouldDelegateTubPlacement(true, null));
    }
}
