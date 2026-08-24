package com.github.ysbbbbbb.kaleidoscopetavern.paper.game.pressing;

/** Source interaction rules shared by the block and wall-furniture adapters. */
public final class PressingTubSemantics {
    private static final String PRESSING_TUB_ITEM = "kaleidoscope_tavern:pressing_tub";

    private PressingTubSemantics() {
    }

    /**
     * Forge gives another tub's placement action priority while the player is
     * using secondary action. The CE wall variant is an interaction entity, so
     * its container adapter must explicitly return PASS for that held tub.
     */
    public static boolean shouldDelegateTubPlacement(
            boolean secondaryUse,
            String heldItemId
    ) {
        return secondaryUse && PRESSING_TUB_ITEM.equals(heldItemId);
    }
}
