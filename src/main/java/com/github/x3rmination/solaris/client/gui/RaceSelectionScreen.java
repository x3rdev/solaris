package com.github.x3rmination.solaris.client.gui;

import com.github.x3rmination.solaris.Solaris;
import com.github.x3rmination.solaris.common.registry.ItemRegistry;
import com.github.x3rmination.solaris.common.solaris.player_affinity.AffinityEnum;
import com.github.x3rmination.solaris.common.solaris.player_class.PlayerClass;
import com.github.x3rmination.solaris.common.solaris.player_race.PlayerRace;
import com.github.x3rmination.solaris.common.solaris.player_race.PlayerRaceRegistry;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.platform.Lighting;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.chat.NarratorChatListener;
import net.minecraft.client.gui.components.MultiLineLabel;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

import java.util.List;

public class RaceSelectionScreen extends Screen {

    private static final ResourceLocation WINDOW_LOCATION = new ResourceLocation(Solaris.MOD_ID, "textures/gui/race/window.png");

    public static final int WINDOW_WIDTH = 202;
    public static final int WINDOW_HEIGHT = 122;
    private PlayerRace selectedRace = null;

    private PlayerClass selectedClass = null;
    private AffinityEnum selectedAffinity = null;
    private MultiLineLabel raceDescription = MultiLineLabel.EMPTY;
    private MultiLineLabel classDescription = MultiLineLabel.EMPTY;
    private boolean secondMenu = false;
    private boolean animating = false;
    private int secondMenuProgress = 0;

    public RaceSelectionScreen() {
        super(NarratorChatListener.NO_TITLE);
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
        int i = (this.width - WINDOW_WIDTH) / 2;
        int j = (this.height - WINDOW_HEIGHT) / 2;
        this.renderBackground(poseStack);
        this.renderWindow(poseStack, i, j);
        if(animating || !this.secondMenu) {
            this.renderAffinities1(poseStack, i, j);
            this.renderSelectRaceButton(poseStack, i, j);
            this.renderSelectedRace(poseStack, i, j);
            this.renderAvatar(i + 101, j + 93, (i + 101) - mouseX, (j + 43) - mouseY);
            this.renderRaceDescription(poseStack, i, j);
        }
        if(this.secondMenu) {
            renderSecondMenu(poseStack, i, j);
            if(!this.animating) {
                this.renderSelectedAffinity(poseStack, i, j);
                this.renderAffinities2(poseStack, i, j);
                this.renderClassButtons(poseStack, i, j);
                this.renderSelectedClass(poseStack, i, j);

                this.renderClassDescription(poseStack, i, j);
                this.renderClassNames(poseStack, i, j);
            } else {
                moveSecondMenu();
            }
        }
    }

    public void renderAffinities1(PoseStack poseStack, int offsetX, int offsetY) {
        if(selectedRace != null) {
            if(selectedRace.equals(PlayerRaceRegistry.UMBRAL)) {
                this.blit(poseStack, offsetX + 122, offsetY + 99, 232, 157, 19, 19);
            }

            if (this.selectedRace.canHaveAffinity(AffinityEnum.WATER)) {
                this.blit(poseStack, offsetX + 128, offsetY + 26, 203, 26, 7, 10);
            }
            if (this.selectedRace.canHaveAffinity(AffinityEnum.FIRE)) {
                this.blit(poseStack, offsetX + 128, offsetY + 37, 203, 37, 7, 10);
            }
            if (this.selectedRace.canHaveAffinity(AffinityEnum.ENERGY)) {
                this.blit(poseStack, offsetX + 128, offsetY + 48, 203, 48, 8, 10);
            }
            if (this.selectedRace.canHaveAffinity(AffinityEnum.NATURE)) {
                this.blit(poseStack, offsetX + 128, offsetY + 59, 203, 59, 8, 9);
            }
            if (this.selectedRace.canHaveAffinity(AffinityEnum.WIND)) {
                this.blit(poseStack, offsetX + 127, offsetY + 69, 203, 69, 10, 8);
            }
            if (this.selectedRace.canHaveAffinity(AffinityEnum.LIGHT)) {
                this.blit(poseStack, offsetX + 127, offsetY + 78, 203, 78, 9, 9);
            }
            if (this.selectedRace.canHaveAffinity(AffinityEnum.DARK)) {
                this.blit(poseStack, offsetX + 128, offsetY + 88, 204, 88, 7, 9);
            }
        }
    }
    
    public void renderAffinities2(PoseStack poseStack, int offsetX, int offsetY) {
        if(this.selectedRace.equals(PlayerRaceRegistry.UMBRAL)) {
            for (int i = 0; i < 7; i++) {
                this.blit(poseStack, offsetX + 19 + (25 * i), offsetY + 26, 44, 138, 15, 14);
            }
            this.blit(poseStack, offsetX + 92, offsetY + 24, 206, 155, 23, 23);
            this.blit(poseStack, offsetX + 94, offsetY + 26, 232, 157, 19, 19);
        }
        if (this.selectedRace.canHaveAffinity(AffinityEnum.WATER)) {
            this.blit(poseStack, offsetX + 23, offsetY + 28, 203, 26, 7, 10);
        }
        if (this.selectedRace.canHaveAffinity(AffinityEnum.FIRE)) {
            this.blit(poseStack, offsetX + 48, offsetY + 28, 203, 37, 7, 10);
        }
        if (this.selectedRace.canHaveAffinity(AffinityEnum.ENERGY)) {
            this.blit(poseStack, offsetX + 73, offsetY + 28, 203, 48, 8, 10);
        }
        if (this.selectedRace.canHaveAffinity(AffinityEnum.NATURE)) {
            this.blit(poseStack, offsetX + 98, offsetY + 29, 203, 59, 8, 9);
        }
        if (this.selectedRace.canHaveAffinity(AffinityEnum.WIND)) {
            this.blit(poseStack, offsetX + 122, offsetY + 30, 203, 69, 10, 8);
        }
        if (this.selectedRace.canHaveAffinity(AffinityEnum.LIGHT)) {
            this.blit(poseStack, offsetX + 147, offsetY + 29, 203, 78, 9, 9);
        }
        if (this.selectedRace.canHaveAffinity(AffinityEnum.DARK)) {
            this.blit(poseStack, offsetX + 173, offsetY + 29, 204, 88, 7, 9);
        }
    }
    public void renderWindow(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        RenderSystem.setShaderTexture(0, WINDOW_LOCATION);
        this.blit(poseStack, pOffsetX, pOffsetY, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(!this.secondMenu) {
            if(this.selectedRace != null && isBetween(mouseX, mouseY, 88, 104, 26, 9)) {
                this.secondMenu = true;
                this.animating = true;
                playClick();
            }

            if (isBetween(mouseX, mouseY, 156, 22, 38, 9)) {
                this.selectedRace = PlayerRaceRegistry.HUMAN;
                playClick();
            }
            if (isBetween(mouseX, mouseY, 156, 34, 38, 9)) {
                this.selectedRace = PlayerRaceRegistry.ELF;
                playClick();
            }
            if (isBetween(mouseX, mouseY, 156, 45, 38, 9)) {
                this.selectedRace = PlayerRaceRegistry.TIEFLING;
                playClick();
            }
            if (isBetween(mouseX, mouseY, 156, 57, 38, 9)) {
                this.selectedRace = PlayerRaceRegistry.ORC;
                playClick();
            }
            if (isBetween(mouseX, mouseY, 156, 69, 38, 9)) {
                this.selectedRace = PlayerRaceRegistry.MINOTAUR;
                playClick();
            }
            if (isBetween(mouseX, mouseY, 156, 81, 38, 9)) {
                this.selectedRace = PlayerRaceRegistry.FAIRY;
                playClick();
            }
            if (isBetween(mouseX, mouseY, 156, 93, 38, 9)) {
                this.selectedRace = PlayerRaceRegistry.UMBRAL;
                playClick();
            }
        } else {
            if (isBetween(mouseX, mouseY, 21, 102, 11, 11)) {
                this.secondMenu = false;
                this.secondMenuProgress = 0;
                this.selectedAffinity = null;
                this.selectedClass = null;
                playClick();
            }

            if(this.selectedRace.canHaveAffinity(AffinityEnum.WATER) && isBetween(mouseX, mouseY, 19, 26, 15, 14)) {
                this.selectedAffinity = AffinityEnum.WATER;
                playClick();
            }
            if(this.selectedRace.canHaveAffinity(AffinityEnum.FIRE) && isBetween(mouseX, mouseY, 44, 26, 15, 14)) {
                this.selectedAffinity = AffinityEnum.FIRE;
                playClick();
            }
            if(this.selectedRace.canHaveAffinity(AffinityEnum.ENERGY) && isBetween(mouseX, mouseY, 69, 26, 15, 14)) {
                this.selectedAffinity = AffinityEnum.ENERGY;
                playClick();
            }
            if(this.selectedRace.canHaveAffinity(AffinityEnum.NATURE) && isBetween(mouseX, mouseY, 94, 26, 15, 14)) {
                this.selectedAffinity = AffinityEnum.NATURE;
                playClick();
            }
            if(this.selectedRace.canHaveAffinity(AffinityEnum.WIND) && isBetween(mouseX, mouseY, 119, 26, 15, 14)) {
                this.selectedAffinity = AffinityEnum.WIND;
                playClick();
            }
            if(this.selectedRace.canHaveAffinity(AffinityEnum.LIGHT) && isBetween(mouseX, mouseY, 144, 26, 15, 14)) {
                this.selectedAffinity = AffinityEnum.LIGHT;
                playClick();
            }
            if(this.selectedRace.canHaveAffinity(AffinityEnum.DARK) && isBetween(mouseX, mouseY, 169, 26, 15, 14)) {
                this.selectedAffinity = AffinityEnum.DARK;
                playClick();
            }

            List<PlayerClass> availableClasses = this.selectedRace.getClasses().stream().filter(playerClass -> playerClass.getAffinity().equals(this.selectedAffinity)).toList();
            if(isBetween(mouseX, mouseY, 11, 49, 38, 9)) {
                this.selectedClass = availableClasses.get(0);
                playClick();
            }
            if(isBetween(mouseX, mouseY, 11, 61, 38, 9)) {
                this.selectedClass = availableClasses.get(1);
                playClick();
            }
            if(isBetween(mouseX, mouseY, 11, 73, 38, 9)) {
                this.selectedClass = availableClasses.get(2);
                playClick();
            }
            if(isBetween(mouseX, mouseY, 11, 85, 38, 9)) {
                this.selectedClass = availableClasses.get(3);
                playClick();
            }
        }
        createDescriptions();
        return super.mouseClicked(mouseX, mouseY, button);
    }

    private void createDescriptions() {
        if(this.selectedRace != null) {
            this.raceDescription = MultiLineLabel.create(this.font, new TranslatableComponent(this.selectedRace.getDescriptionKey()), 84);
        }
        if(this.selectedClass != null) {
            this.classDescription = MultiLineLabel.create(this.font, new TranslatableComponent(this.selectedClass.getDescriptionKey()), 160);
        }
    }

    public boolean isBetween(double mouseX, double mouseY, int originX, int originY, int sizeX, int sizeY) {
        int i = (this.width - WINDOW_WIDTH) / 2;
        int j = (this.height - WINDOW_HEIGHT) / 2;
        return (mouseX >= originX + i && mouseX <= originX + sizeX + i) && (mouseY >= originY + j && mouseY <= originY + sizeY + j);
    }

    public void playClick() {
        Minecraft.getInstance().getSoundManager().play(SimpleSoundInstance.forUI(SoundEvents.UI_BUTTON_CLICK, 1.0F));
    }

    public void renderSelectedRace(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        if(this.selectedRace != null) {
            if (this.selectedRace.equals(PlayerRaceRegistry.HUMAN)) {
                this.blit(poseStack, pOffsetX + 156, pOffsetY + 22, 215, 22, 38, 9);
            }
            if (this.selectedRace.equals(PlayerRaceRegistry.ELF)) {
                this.blit(poseStack, pOffsetX + 156, pOffsetY + 34, 215, 34, 38, 9);
            }
            if (this.selectedRace.equals(PlayerRaceRegistry.TIEFLING)) {
                this.blit(poseStack, pOffsetX + 156, pOffsetY + 46, 215, 46, 38, 9);
            }
            if (this.selectedRace.equals(PlayerRaceRegistry.ORC)) {
                this.blit(poseStack, pOffsetX + 156, pOffsetY + 58, 215, 58, 38, 9);
            }
            if (this.selectedRace.equals(PlayerRaceRegistry.MINOTAUR)) {
                this.blit(poseStack, pOffsetX + 156, pOffsetY + 70, 215, 70, 38, 9);
            }
            if (this.selectedRace.equals(PlayerRaceRegistry.FAIRY)) {
                this.blit(poseStack, pOffsetX + 156, pOffsetY + 82, 215, 82, 38, 9);
            }
            if (this.selectedRace.equals(PlayerRaceRegistry.UMBRAL)) {
                this.blit(poseStack, pOffsetX + 156, pOffsetY + 94, 215, 94, 38, 9);
            }
        }
    }

    public void renderAvatar(int pOffsetX, int pOffsetY, float pMouseX, float pMouseY) {
        if(this.selectedRace != null) {
            if(this.selectedRace.equals(PlayerRaceRegistry.UMBRAL)) {
                Minecraft.getInstance().getItemRenderer().renderGuiItem(ItemRegistry.FIRE_KATANA.get().getDefaultInstance(), pOffsetX - 8, pOffsetY - 48);
            } else {
                RemotePlayer player = new DisplayPlayer(Minecraft.getInstance().level, new GameProfile(null, "_x3r_"), this.selectedRace);
                float f = (float) Math.atan((pMouseX) / 40.0F);
                float f1 = (float) Math.atan((pMouseY) / 40.0F);
                PoseStack posestack = RenderSystem.getModelViewStack();
                posestack.pushPose();
                posestack.translate(pOffsetX, pOffsetY, 1050.0D);
                posestack.scale(1.0F, 1.0F, -1.0F);
                RenderSystem.applyModelViewMatrix();
                PoseStack posestack1 = new PoseStack();
                posestack1.translate(0.0D, 0.0D, 1000.0D);
                posestack1.scale(30, 30, 30);
                Quaternion quaternion = Vector3f.ZP.rotationDegrees(180.0F);
                Quaternion quaternion1 = Vector3f.XP.rotationDegrees(f1 * 20.0F);
                quaternion.mul(quaternion1);
                posestack1.mulPose(quaternion);
                float f2 = player.yBodyRot;
                float f3 = player.getYRot();
                float f4 = player.getXRot();
                float f5 = player.yHeadRotO;
                float f6 = player.yHeadRot;
                player.yBodyRot = 180.0F + f * 20.0F;
                player.setYRot(180.0F + f * 40.0F);
                player.setXRot(-f1 * 20.0F);
                player.yHeadRot = player.getYRot();
                player.yHeadRotO = player.getYRot();
                Lighting.setupForEntityInInventory();
                EntityRenderDispatcher entityrenderdispatcher = Minecraft.getInstance().getEntityRenderDispatcher();
                quaternion1.conj();
                entityrenderdispatcher.overrideCameraOrientation(quaternion1);
                entityrenderdispatcher.setRenderShadow(false);
                MultiBufferSource.BufferSource bufferSource = Minecraft.getInstance().renderBuffers().bufferSource();
                RenderSystem.runAsFancy(() -> entityrenderdispatcher.render(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, posestack1, bufferSource, 15728880));
                bufferSource.endBatch();
                entityrenderdispatcher.setRenderShadow(true);
                player.yBodyRot = f2;
                player.setYRot(f3);
                player.setXRot(f4);
                player.yHeadRotO = f5;
                player.yHeadRot = f6;
                posestack.popPose();
                RenderSystem.applyModelViewMatrix();
                Lighting.setupFor3DItems();
            }
        }
    }

    public void renderRaceDescription(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
        this.raceDescription.renderLeftAlignedNoShadow(poseStack, (pOffsetX + 15)  * 2, (pOffsetY + 26)  * 2, 9, 10526880);
        poseStack.scale(2F, 2F, 2F);
    }

    public void renderSelectRaceButton(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        if(this.selectedRace != null) {
            this.blit(poseStack, pOffsetX + 87, pOffsetY + 103, 203, 0, 28, 11);
        }
    }

    public void moveSecondMenu() {
        if (animating && secondMenuProgress < 101) {
            secondMenuProgress += 2;
        } else {
            animating = false;
            secondMenuProgress--;
        }
    }

    public void renderSecondMenu(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        RenderSystem.setShaderTexture(0, WINDOW_LOCATION);
        this.blit(poseStack, pOffsetX, pOffsetY + 21 + (101 - secondMenuProgress), 0, 155, 202, 102);
    }

    public void renderClassDescription(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        poseStack.scale(0.5F, 0.5F, 0.5F);
        this.classDescription.renderLeftAlignedNoShadow(poseStack, (pOffsetX + 65) * 2, (pOffsetY + 51) * 2, 9, 10526880);
        poseStack.scale(2F, 2F, 2F);
    }

    public void renderClassButtons(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        List<PlayerClass> availableClasses = this.selectedRace.getClasses().stream().filter(playerClass -> playerClass.getAffinity().equals(this.selectedAffinity)).toList();
        for(int i = 0; i < availableClasses.size(); i++) {
            this.blit(poseStack, pOffsetX + 11, pOffsetY + 49 + (12 * i), 206, 196, 38, 9);
        }
    }

    public void renderClassNames(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        List<PlayerClass> availableClasses = this.selectedRace.getClasses().stream().filter(playerClass -> playerClass.getAffinity().equals(this.selectedAffinity)).toList();
        for(int i = 0; i < availableClasses.size(); i++) {
            MultiLineLabel label = MultiLineLabel.create(this.font, new TranslatableComponent(availableClasses.get(i).getName()), 160);
            poseStack.scale(0.5F, 0.5F, 0.5F);
            label.renderLeftAlignedNoShadow(poseStack, (pOffsetX + 13) * 2, (pOffsetY + 52 + (12 * i)) * 2, 9, 0xebebeb);
            poseStack.scale(2F, 2F, 2F);
        }
    }

    public void renderSelectedAffinity(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        if(this.selectedAffinity != null) {
            if (this.selectedAffinity.equals(AffinityEnum.WATER)) {
                this.blit(poseStack, pOffsetX + 18, pOffsetY + 25, 18, 137, 17, 16);
            }
            if (this.selectedAffinity.equals(AffinityEnum.FIRE)) {
                this.blit(poseStack, pOffsetX + 43, pOffsetY + 25, 18, 137, 17, 16);
            }
            if (this.selectedAffinity.equals(AffinityEnum.ENERGY)) {
                this.blit(poseStack, pOffsetX + 68, pOffsetY + 25, 18, 137, 17, 16);
            }
            if (this.selectedAffinity.equals(AffinityEnum.NATURE)) {
                this.blit(poseStack, pOffsetX + 93, pOffsetY + 25, 18, 137, 17, 16);
            }
            if (this.selectedAffinity.equals(AffinityEnum.WIND)) {
                this.blit(poseStack, pOffsetX + 118, pOffsetY + 25, 18, 137, 17, 16);
            }
            if (this.selectedAffinity.equals(AffinityEnum.LIGHT)) {
                this.blit(poseStack, pOffsetX + 143, pOffsetY + 25, 18, 137, 17, 16);
            }
            if (this.selectedAffinity.equals(AffinityEnum.DARK)) {
                this.blit(poseStack, pOffsetX + 168, pOffsetY + 25, 18, 137, 17, 16);
            }
        }
    }

    public void renderSelectedClass(PoseStack poseStack, int pOffsetX, int pOffsetY) {
        if(this.selectedClass != null) {
            List<PlayerClass> availableClasses = this.selectedRace.getClasses().stream().filter(playerClass -> playerClass.getAffinity().equals(this.selectedAffinity)).toList();
            if(availableClasses.contains(this.selectedClass)) {
                this.blit(poseStack, pOffsetX + 10, pOffsetY + 48 + (12 * (availableClasses.indexOf(this.selectedClass))), 205, 183, 40, 11);
            }
        }
    }
}
