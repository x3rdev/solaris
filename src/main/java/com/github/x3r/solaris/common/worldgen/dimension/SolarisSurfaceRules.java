package com.github.x3r.solaris.common.worldgen.dimension;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.SurfaceRules;

public class SolarisSurfaceRules extends SurfaceRules {

    public static SurfaceRules.ConditionSource topOfWorldCheck(int depth) {
        return new TopOfWorldSource(depth);
    }

    public record TopOfWorldSource(int depth) implements SurfaceRules.ConditionSource {

        public static final KeyDispatchDataCodec<TopOfWorldSource> CODEC = KeyDispatchDataCodec.of(RecordCodecBuilder.mapCodec((instance) ->
                instance.group(Codec.INT.fieldOf("depth").forGetter(TopOfWorldSource::depth)).apply(instance, TopOfWorldSource::new)));

        @Override
        public KeyDispatchDataCodec<? extends SurfaceRules.ConditionSource> codec() {
            return CODEC;
        }

        @Override
        public SurfaceRules.Condition apply(final SurfaceRules.Context pContext) {
            class TopOfWorld extends SurfaceRules.LazyYCondition {
                TopOfWorld() {
                    super(pContext);
                }

                protected boolean compute() {
                    return context.chunk.getBlockState(context.pos.above(depth+1)).isAir();
                }
            }

            return new TopOfWorld();
        }
    }
}
