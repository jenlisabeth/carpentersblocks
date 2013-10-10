package carpentersblocks.util.handler;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import carpentersblocks.block.BlockBase;

public class EventHandler
{

	/**
	 * Stores face for onBlockClicked().
	 */
	public static int eventFace;
	
	/**
	 * Stores entity that hit block.
	 */
	public static Entity eventEntity;
	
	@ForgeSubscribe
	/**
	 * Used to store side clicked and also forces onBlockActivated
	 * event when entityPlayer is sneaking and activates block with the
	 * Carpenter's Hammer.
	 */
	public void playerInteractEvent(PlayerInteractEvent event)
	{
		int blockID = event.entity.worldObj.getBlockId(event.x, event.y, event.z);
		
		if (blockID > 0 && Block.blocksList[blockID] instanceof BlockBase)
		{
			BlockBase block = (BlockBase) Block.blocksList[blockID];
			
			eventEntity = event.entity;

			if (event.action.equals(PlayerInteractEvent.Action.LEFT_CLICK_BLOCK)) {

				eventFace = event.face;

			} else if (event.entityPlayer.isSneaking()) {
				
				ItemStack itemStack = event.entityPlayer.getHeldItem();
				
				/*
				 * Call onBlockActivated() if player is holding the Carpenter's Hammer
				 * and sneaking.
				 */
				if (itemStack != null && itemStack.itemID == ItemHandler.itemCarpentersHammerID)
					block.onBlockActivated(event.entity.worldObj, event.x, event.y, event.z, event.entityPlayer, event.face, 1.0F, 1.0F, 1.0F);	
			}
		}
	}

}
