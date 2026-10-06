package com.github.ysbbbbbb.kaleidoscopetavern.paper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CraftEngineVersionGateTest {
    @Test
    void latestDevSnapshotWithoutPatchIsAccepted() {
        assertArrayEquals(new int[]{26, 10},
                KaleidoscopeTavernPlugin.parseVersion("26.10-SNAPSHOT"));
        assertEquals(0, KaleidoscopeTavernPlugin.compareVersion(
                KaleidoscopeTavernPlugin.parseVersion("26.10-SNAPSHOT"), 26, 10, 0));
    }

    @Test
    void olderPatchIsStillRejected() {
        assertEquals(-1, KaleidoscopeTavernPlugin.compareVersion(
                KaleidoscopeTavernPlugin.parseVersion("26.9.9"), 26, 10, 0));
    }
}
