package com.cccstudio.chess_viking_variants.api.test;

import com.cccstudio.chess_viking_variants.api.PlayContext;
import com.cccstudio.chess_viking_variants.api.Variant;

import com.cccstudio.chess_viking_variants.app.ChessVikingVariantsApplication;

public final class VariantTester {

    private VariantTester() {}

    public static void test(Variant variant) {
        PlayContext.get().registerOrSetField("variant", Variant.class, variant);
        ChessVikingVariantsApplication.main({});
    }

}
