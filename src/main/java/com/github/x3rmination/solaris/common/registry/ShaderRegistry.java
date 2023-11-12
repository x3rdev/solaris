package com.github.x3rmination.solaris.common.registry;

import com.github.x3rmination.solaris.Solaris;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterShadersEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;

public class ShaderRegistry {

    private static ShaderInstance rendertypeAbyssalEdge;
    public static void registerShaders(final RegisterShadersEvent event) {
        try {
            event.registerShader(new ShaderInstance(event.getResourceManager(), new ResourceLocation(Solaris.MOD_ID, "rendertype_abyssal_edge"), DefaultVertexFormat.POSITION),
                    shaderInstance -> rendertypeAbyssalEdge = shaderInstance);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static ShaderInstance rendertypeAbyssalEdge() {
        return rendertypeAbyssalEdge;
    }
}
