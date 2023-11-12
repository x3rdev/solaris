package com.github.x3rmination.solaris.client.gui;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.core.ClassEnum;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class ClassSelectionScreen extends Screen {
    public static final ResourceLocation TEX_LOCATION = new ResourceLocation(Solaris.MOD_ID, "textures/gui/class_selection_screen.png");
    public static final ResourceLocation TEX_LOCATION_1 = new ResourceLocation(Solaris.MOD_ID, "textures/gui/class_selection_screen1.png");
    public static final ResourceLocation TEX_LOCATION_2 = new ResourceLocation(Solaris.MOD_ID, "textures/gui/class_selection_screen2.png");

    protected int imageWidth = 217;
    protected int imageHeight = 174;
    protected int leftPos;
    protected int topPos;

    private byte step;
    private byte selectedRace = -1;

    private ClassEnum selectedClass = ClassEnum.DEFAULT_CLASS();
    public ClassSelectionScreen() {
        super(new TextComponent("solaris.screen.class_selection"));
    }

    @Override
    protected void init() {
        super.init();
        this.leftPos = (this.width - this.imageWidth) / 2;
        this.topPos = (this.height - this.imageHeight) / 2;
        this.step = 0;
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);
        this.renderBg(pPoseStack, pMouseX, pMouseY, pPartialTick);
        switch (step) {
            case 0 -> {
                renderRace(pPoseStack, pMouseX, pMouseY, pPartialTick);
            }
            case 1 -> {
                renderClass(pPoseStack, pMouseX, pMouseY, pPartialTick);
            }
            default -> {
                step = 0;
            }
        }
    }

    private void renderBg(PoseStack poseStack, int pX, int pY, float pPartialTick) {
        RenderSystem.setShaderTexture(0, TEX_LOCATION);
        poseStack.pushPose();
        this.blit(poseStack, this.leftPos, this.topPos, 0, 0, imageWidth, imageHeight);
        poseStack.popPose();
    }

    private void renderRace(PoseStack poseStack, int pX, int pY, float pPartialTick) {
        RenderSystem.setShaderTexture(0, TEX_LOCATION_1);
        poseStack.pushPose();
        this.blit(poseStack, this.leftPos+83, this.topPos+68, 50, 0, 50, 76);
        this.blit(poseStack, this.leftPos+25, this.topPos+68, 0, 0, 49, 76);
        for (int i = 0; i < 4; i++) {
            this.blit(poseStack, this.leftPos+141, this.topPos+72+18*i, 101, 1+18*i, 49, 15);
        }
        renderSelectedRace(poseStack, pX, pY, pPartialTick);
        InventoryScreen.renderEntityInInventory(this.leftPos+108, this.topPos+127, 22, (this.leftPos + 108 - pX), (this.topPos + 87 - pY),
                new GuiPlayer(Minecraft.getInstance().level, new GameProfile(null, "test"), new ResourceLocation(Solaris.MOD_ID, "textures/entity/elf.png")));
        poseStack.popPose();
    }

    private void renderSelectedRace(PoseStack poseStack, int pX, int pY, float pPartialTick) {
        switch (selectedRace) {
            case 0 -> {
                this.blit(poseStack, this.leftPos+140, this.topPos+71, 169, 0, 51, 17);
            }
            case 1 -> {
                this.blit(poseStack, this.leftPos+140, this.topPos+71+18, 169, 0, 51, 17);
            }
            case 2 -> {
                this.blit(poseStack, this.leftPos+140, this.topPos+71+18*2, 169, 0, 51, 17);
            }
            case 3 -> {
                this.blit(poseStack, this.leftPos+140, this.topPos+71+18*3, 169, 0, 51, 17);
            }
        }
    }

    private void renderClass(PoseStack poseStack, int pX, int pY, float pPartialTick) {
        RenderSystem.setShaderTexture(0, TEX_LOCATION_1);
        poseStack.pushPose();
        this.blit(poseStack, this.leftPos+25, this.topPos+68, 0, 0, 49, 76);
        this.blit(poseStack, this.leftPos+139, this.topPos+68, 112, 77, 50, 76);
        this.blit(poseStack, this.leftPos+89, this.topPos+89, 71, 97, 37, 37);
        this.blit(poseStack, this.leftPos+75, this.topPos+96, 0, 104, 13, 23);
        this.blit(poseStack, this.leftPos+127, this.topPos+96, 165, 104, 13, 23);
        renderSelectedClass(poseStack, pX, pY, pPartialTick);
        poseStack.popPose();
    }

    private void renderSelectedClass(PoseStack poseStack, int pX, int pY, float pPartialTick) {
        RenderSystem.setShaderTexture(0, TEX_LOCATION_2);
        poseStack.pushPose();
        int x = (selectedClass.ordinal()/9)*27;
        int y = 27*selectedClass.ordinal()-(9*27*x/27);
        this.blit(poseStack, this.leftPos+94, this.topPos+94, x, y, 27, 27);
        poseStack.popPose();
    }

    @Override
    public boolean mouseClicked(double pMouseX, double pMouseY, int pButton) {
        switch (step) {
            case 0 -> {
                if(mouseBetween(pMouseX, pMouseY, this.leftPos+141, this.topPos+72, 49, 15)) {
                    selectedRace = 0;
                    buttonNoise();
                }
                if(mouseBetween(pMouseX, pMouseY, this.leftPos+141, this.topPos+72+18, 49, 15)) {
                    selectedRace = 1;
                    buttonNoise();
                }
                if(mouseBetween(pMouseX, pMouseY, this.leftPos+141, this.topPos+72+18*2, 49, 15)) {
                    selectedRace = 2;
                    buttonNoise();
                }
                if(mouseBetween(pMouseX, pMouseY, this.leftPos+141, this.topPos+72+18*3, 49, 15)) {
                    selectedRace = 3;
                    buttonNoise();
                }

            }
            case 1 -> {
                if(mouseBetween(pMouseX, pMouseY, this.leftPos+75, this.topPos+96, 13, 23)) {
                    int i = selectedClass.ordinal() - 1;
                    selectedClass = ClassEnum.values()[i >= 0 ? i : ClassEnum.values().length-1];
                    buttonNoise();
                }
                if(mouseBetween(pMouseX, pMouseY, this.leftPos+127, this.topPos+96, 13, 23)) {
                    int i = selectedClass.ordinal() + 1;
                    selectedClass = ClassEnum.values()[i%ClassEnum.values().length];
                    buttonNoise();
                }
            }
        }
        if(mouseBetween(pMouseX, pMouseY, this.leftPos+82, this.topPos+152, 52, 22)) {
            step++;
            buttonNoise();
        }
        return super.mouseClicked(pMouseX, pMouseY, pButton);
    }

    private boolean mouseBetween(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height;
    }

    private void buttonNoise() {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }
}
