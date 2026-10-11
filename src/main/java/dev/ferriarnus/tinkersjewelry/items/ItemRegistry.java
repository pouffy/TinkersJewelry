package dev.ferriarnus.tinkersjewelry.items;

import dev.ferriarnus.tinkersjewelry.TinkersJewelry;
import dev.ferriarnus.tinkersjewelry.tools.JewelryDefinitions;
import dev.ferriarnus.tinkersjewelry.tools.stats.BlankBandMaterialStats;
import dev.ferriarnus.tinkersjewelry.tools.stats.GemMaterialStats;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.mantle.item.TooltipItem;
import slimeknights.mantle.registration.deferred.SynchronizedDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.CastItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.library.materials.definition.MaterialVariantId;
import slimeknights.tconstruct.library.tools.helper.ToolBuildHandler;
import slimeknights.tconstruct.library.tools.item.IModifiable;
import slimeknights.tconstruct.library.tools.part.IMaterialItem;
import slimeknights.tconstruct.library.tools.part.ToolPartItem;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ItemRegistry {

	protected static final SynchronizedDeferredRegister<CreativeModeTab> CREATIVE_TABS = SynchronizedDeferredRegister.create(Registries.CREATIVE_MODE_TAB, TinkersJewelry.MODID);

	private static final Item.Properties PARTS_PROPS = new Item.Properties();
	
	private static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(TinkersJewelry.MODID);
	
	
	public static void register() {
		CREATIVE_TABS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	//Materials
	public static final ItemObject<Item> EUDIALYTE = ITEMS.register("eudialyte", () -> new TooltipItem(new Item.Properties()));
	public static final ItemObject<Item> FIRE_OPAL = ITEMS.register("fire_opal", () -> new TooltipItem(new Item.Properties()));
	public static final ItemObject<Item> MALACHITE = ITEMS.register("malachite", () -> new TooltipItem(new Item.Properties()));
	public static final ItemObject<Item> CALCITE = ITEMS.register("calcite", () -> new TooltipItem(new Item.Properties()));
	public static final ItemObject<Item> MILKY_QUARTZ = ITEMS.register("milky_quartz", () -> new TooltipItem(new Item.Properties()));
	public static final ItemObject<Item> MERLINITE = ITEMS.register("merlinite", () -> new TooltipItem(new Item.Properties()));
	public static final ItemObject<Item> PREHNITE = ITEMS.register("prehnite", () -> new TooltipItem(new Item.Properties()));
	public static final ItemObject<Item> RUBELLITE = ITEMS.register("rubellite", () -> new TooltipItem(new Item.Properties()));

	//Toolparts
	public static final ItemObject<ToolPartItem> PLAIN_RING = ITEMS.register("plain_ring", () -> new ToolPartItem(PARTS_PROPS, BlankBandMaterialStats.ID));
	public static final ItemObject<ToolPartItem> GEM = ITEMS.register("gem", () -> new ToolPartItem(PARTS_PROPS, GemMaterialStats.ID));

	//Casts
	public static final CastItemObject PLAIN_RING_CAST = ITEMS.registerCast(PLAIN_RING, PARTS_PROPS);

	//Tools
	public static final ItemObject<CuriosRingItem> RING = ITEMS.register("ring", () -> new CuriosRingItem(PARTS_PROPS, JewelryDefinitions.RING));

	public static final ItemObject<JewelryBookItem> BOOK  = ITEMS.register("book", () -> new JewelryBookItem(new Item.Properties().stacksTo(1)));

	public static final RegistryObject<CreativeModeTab> tabTools = CREATIVE_TABS.register(
			"tools", () -> CreativeModeTab.builder().title(Component.translatable("itemGroup." + TinkersJewelry.MODID + ".all"))
					.icon(() -> {
						MaterialVariantId material;
						if (MaterialRegistry.isFullyLoaded()) {
							material = ToolBuildHandler.RANDOM.getMaterial(GemMaterialStats.ID, RandomSource.create());
						} else {
							material = ToolBuildHandler.getRenderMaterial(0);
						}
						return GEM.get().withMaterialForDisplay(material);
					})
					.displayItems(ItemRegistry::addTabItems)
					.withTabsBefore(TinkerTables.tabTables.getId())
					.build());

	private static void addTabItems(CreativeModeTab.ItemDisplayParameters itemDisplayParameters, CreativeModeTab.Output tab) {
		Consumer<ItemStack> output = tab::accept;
		output.accept(new ItemStack(BOOK));
		acceptTool(output, RING);
		accept(output, PLAIN_RING);
		accept(output, GEM);
		output.accept(new ItemStack(EUDIALYTE.get()));
		output.accept(new ItemStack(FIRE_OPAL.get()));
		output.accept(new ItemStack(MALACHITE.get()));
		output.accept(new ItemStack(CALCITE.get()));
		output.accept(new ItemStack(MILKY_QUARTZ.get()));
		output.accept(new ItemStack(MERLINITE.get()));
		output.accept(new ItemStack(PREHNITE.get()));
		output.accept(new ItemStack(RUBELLITE.get()));
		output.accept(new ItemStack(PLAIN_RING_CAST.get()));
		output.accept(new ItemStack(PLAIN_RING_CAST.getSand()));
		output.accept(new ItemStack(PLAIN_RING_CAST.getRedSand()));
	}

	private static void acceptTool(Consumer<ItemStack> output, Supplier<? extends IModifiable> tool) {
		ToolBuildHandler.addVariants(output, tool.get(), "");
	}

	private static void accept(Consumer<ItemStack> output, Supplier<? extends IMaterialItem> item) {
		item.get().addVariants(output, "");
	}
}
