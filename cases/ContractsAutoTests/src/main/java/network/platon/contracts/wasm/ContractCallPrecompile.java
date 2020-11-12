package network.platon.contracts.wasm;

import com.platon.rlp.datatypes.Uint64;
import com.platon.rlp.datatypes.Uint8;
import java.math.BigInteger;
import java.util.Arrays;
import org.web3j.abi.WasmFunctionEncoder;
import org.web3j.abi.datatypes.WasmFunction;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.WasmContract;
import org.web3j.tx.gas.GasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://github.com/PlatONnetwork/client-sdk-java/releases">platon-web3j command line tools</a>,
 * or the org.web3j.codegen.WasmFunctionWrapperGenerator in the 
 * <a href="https://github.com/PlatONnetwork/client-sdk-java/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with platon-web3j version 0.13.1.5.
 */
public class ContractCallPrecompile extends WasmContract {
    private static String BINARY_0 = "0x0061736d0100000001671060027f7f0060037f7f7f0060017f017f60017f0060027f7f017f60000060077f7f7f7f7f7e7e0060057f7f7f7e7e006000017f60037f7f7f017f60017f017e60047f7f7f7f0060027f7e0060047f7f7f7f017f60077f7f7f7f7f7f7f017f60047f7f7e7f017f0286020b03656e760c706c61746f6e5f70616e6963000503656e761d706c61746f6e5f6765745f63616c6c5f6f75747075745f6c656e677468000803656e7616706c61746f6e5f6765745f63616c6c5f6f7574707574000303656e760d726c705f6c6973745f73697a65000203656e760f706c61746f6e5f726c705f6c697374000103656e760e726c705f62797465735f73697a65000403656e7610706c61746f6e5f726c705f6279746573000103656e7617706c61746f6e5f6765745f696e7075745f6c656e677468000803656e7610706c61746f6e5f6765745f696e707574000303656e760b706c61746f6e5f63616c6c000e03656e760d706c61746f6e5f72657475726e0000033e3d05060009010404000f01070007070606060702000302000102010109000c04010305020d010a0a0200030200000300000500040002000400010502020b0405017001070705030100020608017f0141d08d040b073904066d656d6f72790200115f5f7761736d5f63616c6c5f63746f7273000b0f5f5f66756e63735f6f6e5f65786974003b06696e766f6b65002c090c010041010b06151718191b1c0aa2733d040010440b980201027f230041e0006b22072400200720063703582007410036025020074200370348200741c8006a4120100d200728024841004120100e1a2007280248220820023a001f2008200728024c2001100f200328020020032802042001100f200428020020042802042001100f200741386a200110102101200741106a200741286a41d20b101110120240024020072d0024450d00200741106a20012005200741d8006a1013450d00200741003602082007420037030020071001100d20072802001002200020072802002007280204101420072802002204450d01200720043602040c010b200041ab0810111a0b200128020022040440200120043602040b2007280248220104402007200136024c0b200741e0006a24000bfa0101057f230041206b22022400024020002802042203200028020022046b22052001490440200028020820036b200120056b22044f04400340200341003a00002000200028020441016a22033602042004417f6a22040d000c030b000b2000200110292106200241186a200041086a3602002002410036021441002101200604402006101d21010b200220013602082002200120056a22033602102002200120066a3602142002200336020c0340200341003a00002002200228021041016a22033602102004417f6a22040d000b2000200241086a101e200241086a101f0c010b200520014d0d002000200120046a3602040b200241206a24000bfc0202027f017e02402002450d00200020013a0000200020026a2203417f6a20013a000020024103490d00200020013a0002200020013a00012003417d6a20013a00002003417e6a20013a000020024107490d00200020013a00032003417c6a20013a000020024109490d002000410020006b41037122046a2203200141ff017141818284086c22013602002003200220046b417c7122046a2202417c6a200136020020044109490d002003200136020820032001360204200241786a2001360200200241746a200136020020044119490d002003200136021820032001360214200320013602102003200136020c200241706a20013602002002416c6a2001360200200241686a2001360200200241646a20013602002001ad220542208620058421052004200341047141187222016b2102200120036a2101034020024120490d0120012005370300200141186a2005370300200141106a2005370300200141086a2005370300200141206a2101200241606a21020c000b000b20000bcd0101057f230041206b22042400200241086a2106034020002001464504400240200228020422032002280208470440200320002d00003a00002002200228020441016a3602040c010b2002200320022802006b220741016a10292105200420063602184100210320044100360214200504402005101d21030b200420033602082004200320056a3602142004200320076a220336020c200320002d00003a00002004200341016a3602102002200441086a101e200441086a101f0b200041016a21000c010b0b200441206a24000b5a01017f20004200370200200041003602080240200128020420012802006b2202450d002000200210272001280204200128020022026b22014101480d0020002802042002200110261a2000200028020420016a3602040b20000b910101027f20004200370200200041086a410036020020012102024003402002410371044020022d0000450d02200241016a21020c010b0b2002417c6a21020340200241046a22022802002203417f73200341fffdfb776a7141808182847871450d000b0340200341ff0171450d01200241016a2d00002103200241016a21020c000b000b20002001200220016b102220000bc412010d7f23004190016b22022400200241086a41bd081011210b2001280208200141016a220c20012d0000220341017122051b21072001280204200341017620051b21094100210341012104024003402004410171410020032009491b0440200320076a2d00002205415f6a41ff017141de004921042006200541bf7f6a41ff0171411a4972210620082005419f7f6a41ff0171411a49722108200341016a21030c01050240200420062008714101737121062009450d002009210303402003450d01200320076a21042003417f6a220521032004417f6a2d00004131470d000b0c030b0b0b417f21050b024002402006410171450d00200541076a20094b0d00200541016a4102490d00200941da004b0d00410021032002410036026020024200370358200241d8006a20092005417f7322086a100d4101210602400240024003402003200128020420012d00002204410176200441017122041b20086a4f044002402006410171450d05200241c8006a1023220341016a210a410021040340200420054604402003280204220820022d004822054101762207200541017122061b2204200b28020420022d00082205410176200541017122051b470d05200b280208200b41016a20051b210520060d0220072104200a210603402004450440200721080c080b20062d000020052d0000470d06200541016a2105200641016a21062004417f6a21040c000b00052001280208200c20012d00004101711b20046a2c000022074120722007200741bf7f6a411a491b210d024002400240027f20022d004822094101712207450440410a210820094101760c010b20032802002209417e71417f6a210820032802040b220620084604402003280208200a20094101711b210e416f2109200841e6ffffff074d0440410b20084101742207200841016a220920092007491b220741106a4170712007410b491b21090b2009101d2207200e2008102420032009410172360200200320073602080c010b2007450d01200328020821070b2003200641016a3602040c010b2002200641017441026a3a0048200a21070b200620076a220641003a00012006200d3a0000200441016a21040c010b000b000b05200228025820036a2001280208200c20041b20056a20036a41016a2d000041d0086a2d000022043a00002006200441ff0147712106200341016a21030c010b0b2004450d012003280208210603402004450d0220062d000020052d0000470d01200541016a2105200641016a21062004417f6a21040c000b000b20004200370000200041003a0014200041086a4200370000200041106a410036000020022802582203450d032002200336025c0c030b410021052002410036027020024200370368200241e8006a2008410174410172100d03402005200328020420022d00482204410176200441017122041b22064f450440200228026820056a2003280208200a20041b20056a2d000022044105763a00002002280268200328020420022d0048220641017620064101711b6a20056a41016a2004411f713a0000200541016a21050c010b0b200228026820066a41003a0000200228026c21040240200228025c2205200228025822036b22064101480d0020062002280270220720046b4c0440034020032005460d02200420032d00003a00002002200228026c41016a220436026c200341016a21030c000b000b20024188016a200241f0006a3602004100210820024100360284012004200228026822016b2109200420066a20016b2206200720016b2207410174220120012006491b41ffffffff07200741ffffffff03491b220704402007101d21080b200220083602782002200820096a2206360280012002200720086a360284012002200636027c200241f8006a410472210803402003200546450440200620032d00003a0000200220022802800141016a220636028001200341016a21030c010b0b20022802682004200810250240200228026c220520046b220341004c044020022802800121040c010b200220022802800120042003102620036a220436028001200228026c21050b200228026821032002200228027c3602682002200336027c2002200436026c2002200536028001200228027021052002200228028401360270200220053602840120022003360278200241f8006a101f200228026c21040b2002420037026c2002280268210520024100360268200420056b21044101210303402004044020052d000041002003411d764101716b41b3c5d1d0027141002003411c764101716b41dde788ea037141002003411b764101716b41fab384f5017141002003411a764101716b41ed9cc2b20271410020034119764101716b41b2afa9db0371200341057441e0ffffff037173737373737321032004417f6a2104200541016a21050c010b0b20034101470d00410021062002410036027020024200370368200241003602800120024200370378027f4100200228025c417a6a2204200228025822036b2205450d001a200241f8006a2005102703402003200446450440200228027c220520032d00003a00002002200541016a36027c200341016a21030c010b0b20022802782106200228027c0b20066b2108410021034100210541002104024003402004200846044002402005410820036b7441ff0171452003410548712103200604402002200636027c0b20030d00200228026821030c030b05200420066a2d0000200541057441e01f71722105200341056a21030340200341084845044020022005200341786a2203763a008f01200241e8006a2002418f016a10210c010b0b200441016a21040c010b0b200228026c200228026822036b4114470d0020024188016a410036020020024180016a4200370300200242003703784100210303402003411446450440200241f8006a20036a41003a0000200341016a21030c010b0b200228026c200228026822056b2104410021030340024020032004460d00200341134b0d00200241f8006a20036a200320056a2d00003a0000200341016a21030c010b0b20002002290378370000200041013a0014200041106a20024188016a280200360000200041086a20024180016a290300370000200504402002200536026c0b20022802582203450d032002200336025c0c030b2003450d002002200336026c0b20022802582203450d002002200336025c0b20004200370000200041003a0014200041086a4200370000200041106a41003600000b20024190016a24000b7e01017f230041206b22042400200441106a20021028200420032903001028200020012802002203200128020420036b20042802102201200428021420016b20042802002201200428020420016b10092101200428020022000440200420003602040b200428021022000440200420003602140b200441206a24002001450bd406010b7f230041106b220b2400200b41ab081011210320004200370200200041086a4100360200024002402003280204200b2d0000220641017620064101711b220a200220016b4101746a2205410b4f0440200541106a4170712206101d21042000200536020420002006410172360200200020043602080c010b200020054101743a0000200041016a21042005450d010b200441302005100e2104200b2d000021060b200420056a41003a0000200341016a2108200328020821092003280204200641fe01714101762006410171220c1b2103027f20002d00002207410171220d450440410a210420074101762206200a200a20064b1b0c010b20002802002207417e71417f6a210420002802042206200a2006200a491b0b210520092008200c1b2108024002400240027f02400240200520066b20046a20034f0440027f200d044020002802080c010b200041016a0b21044100210720032005460440200321090c050b200620056b220c450440200521090c050b20032005490d01200420084f0d02200420066a20084d0d02200420056a20084d04402008200320056b6a21080c030b200420082005102a200320086a2108200320056b21032005210741000c030b027f2007410171044020002802080c010b200041016a0b2109416f2107200441e6ffffff074d0440410b20044101742204200320066a20056b220720072004491b220441106a4170712004410b491b21070b2007101d2204200820031024200620056b22060440200320046a200520096a200610240b200020043602082000200320066a220336020420002007410172360200200320046a41003a00000c050b200420082003102a200320046a200420056a200c102a200521090c030b20050b2109200420076a220520036a200520096a200c102a0b200420076a20082003102a0b200320096b20066a2103024020002d00004101710440200020033602040c010b200020034101743a00000b200320046a41003a00000b200041016a2103034020012002464504402000280208200320002d00004101711b200a6a20012d000041047641ac086a2d00003a00002000280208200320002d00004101711b200a6a41016a20012d0000410f7141ac086a2d00003a0000200141016a2101200a41026a210a0c010b0b200b41106a24000baf0100230041d0006b2201240020012004370348200141386a20021016200141106a200141286a41a70b101110120240024020012d0024450d00200141106a200141386a2003200141c8006a1013450d00200141003602082001420037030020011001100d20012802001002200020012802002001280204101420012802002200450d01200120003602040c010b200041ab0810111a0b2001280238220004402001200036023c0b200141d0006a24000bfc0301067f230041306b22022400027f41002001280204220520012d000022034101762206200341017122031b22074102490d001a41002001280208200141016a20031b22042d00004130470d001a20042d000141f800464101740b21042002410036021020024200370308200720046b41016a41017622070440200241286a200241106a36020020022007101d2203360220200220033602182002200336021c2002200320076a360224200241086a200241186a101e200241186a101f20012d00002203410176210620012802042105200341017121030b0240024002402005200620031b41017104402001280208200141016a20031b20046a2c000010202203417f460d01200220033a0018200241086a200241186a1021200441017221040b200141016a2106024003402004200128020420012d00002203410176200341017122031b4f0d012001280208200620031b20046a22052c000010202103200541016a2c00001020210502402003417f460d002005417f460d002002200520034104746a3a0018200441026a2104200241086a200241186a10210c010b0b20004100360208200042003702000c020b200020022802083602002000200229020c37020420024100360210200242003703080c020b20004100360208200042003702000b20022802082204450d002002200436020c0b200241306a24000baf0100230041d0006b2201240020012004370348200141386a20021016200141106a200141286a41fc0a101110120240024020012d0024450d00200141106a200141386a2003200141c8006a1013450d00200141003602082001420037030020011001100d20012802001002200020012802002001280204101420012802002200450d01200120003602040c010b200041ab0810111a0b2001280238220004402001200036023c0b200141d0006a24000baf0100230041d0006b2201240020012004370348200141386a20021016200141106a200141286a41d10a101110120240024020012d0024450d00200141106a200141386a2003200141c8006a1013450d00200141003602082001420037030020011001100d20012802001002200020012802002001280204101420012802002200450d01200120003602040c010b200041ab0810111a0b2001280238220004402001200036023c0b200141d0006a24000bda0201017f230041e0006b22012400200120063703582001410036025020014200370348200141c8006a4120100d200128024841004120100e1a2001280248220741203a001f20014100360240200142003703382007200128024c200141386a100f2001280248200128024c200141386a100f2001280248200128024c200141386a100f20022802002002280204200141386a100f20032802002003280204200141386a100f20042802002004280204200141386a100f200141106a200141286a41a60a101110120240024020012d0024450d00200141106a200141386a2005200141d8006a1013450d00200141003602082001420037030020011001100d20012802001002200020012802002001280204101420012802002204450d01200120043602040c010b200041ab0810111a0b2001280238220404402001200436023c0b2001280248220404402001200436024c0b200141e0006a24000bfa0101017f230041d0006b2207240020072006370348200741003602402007420037033820012802002001280204200741386a100f20022802002002280204200741386a100f20032802002003280204200741386a100f20042802002004280204200741386a100f200741106a200741286a41fb09101110120240024020072d0024450d00200741106a200741386a2005200741c8006a1013450d00200741003602082007420037030020071001100d20072802001002200020072802002007280204101420072802002204450d01200720043602040c010b200041ab0810111a0b2007280238220404402007200436023c0b200741d0006a24000be70100230041d0006b2201240020012006370348200141003602402001420037033820022802002002280204200141386a100f20032802002003280204200141386a100f20042802002004280204200141386a100f200141106a200141286a41d009101110120240024020012d0024450d00200141106a200141386a2005200141c8006a1013450d00200141003602082001420037030020011001100d20012802001002200020012802002001280204101420012802002204450d01200120043602040c010b200041ab0810111a0b2001280238220404402001200436023c0b200141d0006a24000baf0100230041d0006b2201240020012004370348200141386a20021016200141106a200141286a418008101110120240024020012d0024450d00200141106a200141386a2003200141c8006a1013450d00200141003602082001420037030020011001100d20012802001002200020012802002001280204101420012802002200450d01200120003602040c010b200041ab0810111a0b2001280238220004402001200036023c0b200141d0006a24000b0b002000410120001b102d0b6701017f20002802002000280204200141046a1025200028020021022000200128020436020020012002360204200028020421022000200128020836020420012002360208200028020821022000200128020c3602082001200236020c200120012802043602000b2b01027f200028020821012000280204210203402001200247044020002001417f6a22013602080c010b0b0b4801017f415021010240200041506a41ff0171410a490d0041a97f21012000419f7f6a41ff01714106490d00200041496a417f200041bf7f6a41ff01714106491b0f0b200020016a0bb70101047f230041206b220224000240200028020422032000280208490440200320012d00003a00002000200028020441016a3602040c010b2000200320002802006b220441016a10292105200241186a200041086a3602004100210320024100360214200504402005101d21030b20022003360208200320046a220420012d00003a00002002200320056a3602142002200436020c2002200441016a3602102000200241086a101e200241086a101f0b200241206a24000b5a01027f02402002410a4d0440200020024101743a0000200041016a21030c010b200241106a4170712204101d21032000200236020420002004410172360200200020033602080b2003200120021024200220036a41003a00000b190020004200370200200041086a41003602002000102b20000b10002002044020002001200210261a0b0b270020022002280200200120006b22016b2202360200200141014e044020022000200110261a0b0bfc0801067f03400240200020046a2105200120046a210320022004460d002003410371450d00200520032d00003a0000200441016a21040c010b0b200220046b210602402005410371220745044003402006411049450440200020046a2203200120046a2205290200370200200341086a200541086a290200370200200441106a2104200641706a21060c010b0b027f2006410871450440200120046a2103200020046a0c010b200020046a2205200120046a2204290200370200200441086a2103200541086a0b21042006410471044020042003280200360200200341046a2103200441046a21040b20064102710440200420032f00003b0000200341026a2103200441026a21040b2006410171450d01200420032d00003a000020000f0b024020064120490d002007417f6a220741024b0d00024002400240024002400240200741016b0e020102000b2005200120046a220328020022073a0000200541016a200341016a2f00003b0000200041036a2108200220046b417d6a2106034020064111490d03200420086a2203200120046a220541046a2802002202410874200741187672360200200341046a200541086a2802002207410874200241187672360200200341086a2005410c6a28020022024108742007411876723602002003410c6a200541106a2802002207410874200241187672360200200441106a2104200641706a21060c000b000b2005200120046a220328020022073a0000200541016a200341016a2d00003a0000200041026a2108200220046b417e6a2106034020064112490d03200420086a2203200120046a220541046a2802002202411074200741107672360200200341046a200541086a2802002207411074200241107672360200200341086a2005410c6a28020022024110742007411076723602002003410c6a200541106a2802002207411074200241107672360200200441106a2104200641706a21060c000b000b2005200120046a28020022073a0000200041016a21082004417f7320026a2106034020064113490d03200420086a2203200120046a220541046a2802002202411874200741087672360200200341046a200541086a2802002207411874200241087672360200200341086a2005410c6a28020022024118742007410876723602002003410c6a200541106a2802002207411874200241087672360200200441106a2104200641706a21060c000b000b200120046a41036a2103200020046a41036a21050c020b200120046a41026a2103200020046a41026a21050c010b200120046a41016a2103200020046a41016a21050b20064110710440200520032d00003a00002005200328000136000120052003290005370005200520032f000d3b000d200520032d000f3a000f200541106a2105200341106a21030b2006410871044020052003290000370000200541086a2105200341086a21030b2006410471044020052003280000360000200541046a2105200341046a21030b20064102710440200520032f00003b0000200541026a2105200341026a21030b2006410171450d00200520032d00003a00000b20000b2001017f20002001101d2202360200200020023602042000200120026a3602080b6302017f017e20012103034020035045044020034208882103200241016a21020c010b0b200041003602082000420037020020002002100d2000280204417f6a21020340200150450440200220013c00002002417f6a2102200142088821010c010b0b0b2e01017f2001200028020820002802006b2200410174220220022001491b41ffffffff07200041ffffffff03491b0b0f002002044020002001200210430b0b2201017f03402001410c470440200020016a4100360200200141046a21010c010b0b0be50a020a7f027e23004180026b22002400104410072201102d22021008200041406b200041086a20022001411c102e22014100102f02400240200041406b1030220a500d0041fd0b1031200a510440200110324101460d020c010b41820c1031200a510440200041e0006a4200370300200041d8006a4200370300200042003703702000420037036820004200370350200041003a004c200041003602482000420037034020011032410747044010000b200041013602d001200020013602e0012000200041d0016a3602e401200041e0016a200041406b1033200041206a200120002802d001102f200041206a103402400240200041206a1035450d002000280224450d0020002802202d000041c001490d010b10000b200041f0016a200041206a103620002802f401220141024f044010000b200041f0006a2104200041e8006a210520002802f00121020340200104402001417f6a210120022d00002103200241016a21020c010b0b200020033a004c200020002802d00141016a3602d001200041e0016a200041d0006a22021033200041e0016a200041dc006a22031033200041e0016a20051037200041e0016a20041037200041c0016a200041406b1010210120002d004c2107200041b0016a200210102102200041a0016a2003101021032000290370210a2000290368210b200041206a200041f0016a2001101022042007200041e0016a200210102205200041d0016a200310102206200b200a100c200628020022070440200620073602040b200528020022060440200520063602040b200428020022050440200420053602040b200041206a1038200328020022040440200320043602040b200228020022030440200220033602040b200128020022020440200120023602040b200028025c22010440200020013602600b200028025022010440200020013602540b20002802402201450d02200020013602440c020b41970c1031200a5104402001410110390c020b41ad0c1031200a5104402001410210390c020b41c60c1031200a5104402001410310390c020b41da0c1031200a51044020014104103a0c020b41ef0c1031200a510440200041e8006a4200370300200041e0006a4200370300200041d0006a42003703002000420037037820004200370370200042003703582000420037034820004200370340200041f8006a2102200041f0006a210320011032410747044010000b200041013602f001200020013602202000200041f0016a360224200041206a200041406b1033200041206a200041cc006a22041033200041206a200041d8006a22051033200041206a200041e4006a22061033200041206a20031037200041206a20021037200041b0016a200041406b10102101200041a0016a20041010210220004190016a20051010210320004180016a2006101021042000290378210a2000290370210b200041206a200041f0016a200110102205200041e0016a200210102206200041d0016a200310102207200041c0016a200410102208200b200a101a200828020022090440200820093602040b200728020022080440200720083602040b200628020022070440200620073602040b200528020022060440200520063602040b200041206a1038200428020022050440200420053602040b200328020022040440200320043602040b200228020022030440200220033602040b200128020022020440200120023602040b200028026422010440200020013602680b2000280258220104402000200136025c0b200028024c22010440200020013602500b20002802402201450d02200020013602440c020b41830d1031200a51044020014105103a0c020b419d0d1031200a520d002001410610390c010b10000b103b20004180026a24000b9b0101047f230041106b220124002001200036020c2000047f41cc0d200041086a2202411076220041cc0d2802006a220336020041c80d41c80d280200220420026a41076a417871220236020002400240200341107420024d044041cc0d200341016a360200200041016a21000c010b2000450d010b200040000d0010000b20042001410c6a4104102641086a0541000b2100200141106a240020000b730020004200370210200042ffffffff0f370208200020023602042000200136020002402003410871450d002000104520024f0d002003410471044010000c010b200042003702000b02402003411071450d002000104520024d0d0020034104710440100020000f0b200042003702000b20000bc90202067f017e230041106b220324002001280208220520024b0440200341086a2001103c20012003280208200328020c103d36020c20032001103c410021052001027f410020032802002207450d001a410020032802042208200128020c2206490d001a200820062006417f461b210420070b360210200141146a2004360200200141003602080b200141106a210603402001280214210402402005200249044020040d01410021040b2000200628020020044114102e1a200341106a24000f0b20032001103c41002104027f410020032802002205450d001a410020032802042208200128020c2207490d001a200820076b2104200520076a0b2105200120043602142001200536021020032006410020052004103d10472001200329030022093702102001200128020c2009422088a76a36020c2001200128020841016a22053602080c000b000b850102027f017e230041106b22012400200010340240024020001035450d002000280204450d0020002802002d000041c001490d010b10000b200141086a20001036200128020c220041094f044010000b200128020821020340200004402000417f6a210020023100002003420886842103200241016a21020c010b0b200141106a240020030b3901027e42a5c688a1c89ca7f94b210103402000300000220250450440200041016a2100200142b383808080207e20028521010c010b0b20010b800101047f230041106b2201240002402000280204450d0020002802002d000041c001490d00200141086a2000103c200128020c210003402000450d01200141002001280208220320032000103d22046a20034520002004497222031b3602084100200020046b20031b2100200241016a21020c000b000b200141106a240020020b4301017f230041206b22022400200241086a20002802002000280204280200102f200241086a2001103e20002802042200200028020041016a360200200241206a24000b4101017f200028020445044010000b0240200028020022012d0000418101470d00200028020441014d047f100020002802000520010b2c00014100480d0010000b0b980101037f200028020445044041000f0b20001034200028020022022c0000220141004e044020014100470f0b027f4101200141807f460d001a200141ff0171220341b7014d0440200028020441014d047f100020002802000520020b2d00014100470f0b4100200341bf014b0d001a2000280204200141ff017141ca7e6a22014d047f100020002802000520020b20016a2d00004100470b0bd50101047f2001103f2204200128020422024b04401000200128020421020b200128020021052000027f02400240200204404100210120052c00002203417f4a0d01027f200341ff0171220141bf014d04404100200341ff017141b801490d011a200141c97e6a0c010b4100200341ff017141f801490d001a200141897e6a0b41016a21010c010b4101210120050d000c010b41002103200120046a20024b0d0020022001490d00410020022004490d011a200120056a2103200220016b20042004417f461b0c010b41000b360204200020033602000b4601017f230041206b22022400200241086a20002802002000280204280200102f2001200241086a103037030020002802042200200028020041016a360200200241206a24000bbb0301067f230041406a220224002002410036022020024200370318200241186a410010402002412c6a41003602002002420037022441012103024002400240200241306a20001041220428020420022d00302201410176200141017122051b220141014d0440200141016b0d030c010b20014138490d01200141016a210303402001450d03200341016a2103200141087621010c000b000b2004280208200441016a20051b2c0000417f4a0d010b200141016a21030b20022802202003490440200241186a200310400b200241186a200241086a200010412201280208200141016a20012d0000220341017122001b22042001280204200341017620001b22011005200228021c22036a1042200420012003200228021822056a10060340024020022802282203200228022422044622060d00200341786a220128020022004504401000200128020021000b20012000417f6a220036020020000d0020022001360228200241186a2003417c6a2802002201200228021c20016b220310036a10422001200228021822056a22012003200110040c010b0b200645044010000b2005200228021c100a20040440200220043602280b200241406b24000ba20401067f230041e0006b22022400200210232106200242003703182002420037031020001032410447044010000b2002410136022c2002200036022020022002412c6a360224200241306a20004101102f024002402002280234044020022802302d000041c001490d010b200241d0006a10231a0c010b200241c8006a200241306a1036200241306a103f21030240024002400240200228024822000440200228024c220420034f0d010b41002100200241d8006a410036020020024200370350410021040c010b200241d8006a4100360200200242003703502000200420032003417f461b22056a21042005410a4b0d010b200220054101743a0050200241d0006a41017221030c010b200541106a4170712207101d21032002200536025420022007410172360250200220033602580b034020002004470440200320002d00003a0000200341016a2103200041016a21000c010b0b200341003a00000b200241186a2100200241106a2103024020022d0000410171450440200241003b01000c010b200228020841003a00002002410036020420022d0000410171450d00200241003602000b200241086a200241d8006a28020036020020022002290350370300200241d0006a102b20022802242204200428020041016a360200200241206a20031037200241206a20001037200241306a200241c8006a200241d0006a20061041200229031020022903182001110700200241306a1038200241e0006a24000bce0302067f027e230041b0016b22022400200241286a4100360200200241186a42003703002002420037033820024200370330200242003703202002420037031020024200370308200241386a2103200241306a210420001032410647044010000b2002410136029001200220003602a001200220024190016a3602a401200241a0016a200241086a1033200241a0016a200241146a22051033200241a0016a200241206a22061033200241a0016a20041037200241a0016a20031037200241e0006a200241086a10102100200241d0006a200510102103200241406b2006101021042002290338210820022903302109200241a0016a200220024190016a20001010220720024180016a200310102205200241f0006a200410102206200920082001110600200628020022010440200620013602040b200528020022010440200520013602040b200728020022010440200720013602040b200241a0016a1038200428020022010440200420013602040b200328020022040440200320043602040b200028020022030440200020033602040b200228022022000440200220003602240b200228021422000440200220003602180b2002280208220004402002200036020c0b200241b0016a24000b880101037f41b80d410136020041bc0d2802002100034020000440034041c00d41c00d2802002201417f6a2202360200200141014845044041b80d4100360200200020024102746a22004184016a280200200041046a28020011030041b80d410136020041bc0d28020021000c010b0b41c00d412036020041bc0d200028020022003602000c010b0b0b2101017f2001103f220220012802044b044010000b2000200120011046200210470b2701017f230041206b22022400200241086a200020014114102e10452100200241206a240020000bcb0101037f230041206b22022400024002402000280204044020002802002d000041c001490d010b20024100360208200242003703000c010b200241186a2000103620022802182103200241106a20001036200228021021042000103f21002002410036020820024200370300200020046a20036b2200450d0020022000102720004101480d002002200228020420032000102620006a3602040b2001280200044020014100360208200142003702000b2001200228020036020020012002290204370204200241206a24000bff0201037f200028020445044041000f0b2000103441012102024020002802002c00002201417f4a0d00200141ff0171220341b7014d0440200341807f6a0f0b02400240200141ff0171220141bf014d0440024020002802042201200341c97e6a22024d047f100020002802040520010b4102490d0020002802002d00010d0010000b200241054f044010000b20002802002d000145044010000b4100210241b7012101034020012003460440200241384f0d030c0405200028020020016a41ca7e6a2d00002002410874722102200141016a21010c010b000b000b200141f7014d0440200341c07e6a0f0b024020002802042201200341897e6a22024d047f100020002802040520010b4102490d0020002802002d00010d0010000b200241054f044010000b20002802002d000145044010000b4100210241f701210103402001200346044020024138490d0305200028020020016a418a7e6a2d00002002410874722102200141016a21010c010b0b0b200241ff7d490d010b10000b20020b2f01017f200028020820014904402001102d200028020020002802041026210220002001360208200020023602000b0b4d01017f20004200370200200041086a2202410036020020012d0000410171450440200020012902003702002002200141086a28020036020020000f0b200020012802082001280204102220000b3601017f200028020820014904402001102d200028020020002802041026210220002001360208200020023602000b200020013602040b8d0301037f024020002001460d00200120006b20026b410020024101746b4d044020002001200210261a0c010b20002001734103712103027f024020002001490440200020030d021a410021030340200120036a2105200020036a2204410371450440200220036b210241002103034020024104490d04200320046a200320056a280200360200200341046a21032002417c6a21020c000b000b20022003460d04200420052d00003a0000200341016a21030c000b000b024020030d002001417f6a21040340200020026a22034103714504402001417c6a21032000417c6a2104034020024104490d03200220046a200220036a2802003602002002417c6a21020c000b000b2002450d042003417f6a200220046a2d00003a00002002417f6a21020c000b000b2001417f6a210103402002450d03200020026a417f6a200120026a2d00003a00002002417f6a21020c000b000b200320056a2101200320046a0b210303402002450d01200320012d00003a00002002417f6a2102200341016a2103200141016a21010c000b000b0b3501017f230041106b220041d08d0436020c41c40d200028020c41076a417871220036020041c80d200036020041cc0d3f003602000b2e01017f200028020445044041000f0b4101210120002802002c0000417f4c047f200010462000103f6a0520010b0b5b00027f027f41002000280204450d001a410020002802002c0000417f4a0d011a20002802002d0000220041bf014d04404100200041b801490d011a200041c97e6a0c010b4100200041f801490d001a200041897e6a0b41016a0b0b5b01027f2000027f0240200128020022054504400c010b200220036a200128020422014b0d0020012002490d00410020012003490d011a200220056a2104200120026b20032003417f461b0c010b41000b360204200020043602000b0bb20502004180080b406c61783171717171717171717171717171717171717171717171717171717171717171673979756c3230000030313233343536373839616263646566006c61780041d0080be404ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff0fff0a1115141a1e0705ffffffffffffff1dff180d19090817ff12161f1b13ff010003100b1c0c0e060402ffffffffffff1dff180d19090817ff12161f1b13ff010003100b1c0c0e060402ffffffffff6c6178317171717171717171717171717171717171717171717171717171717171717138366630723468006c6178317171717171717171717171717171717171717171717171717171717171717178386c6d6b6739006c6178317171717171717171717171717171717171717171717171717171717171717139667677717836006c6178317171717171717171717171717171717171717171717171717171717171717179353636346d67006c6178317171717171717171717171717171717171717171717171717171717171717172347264393664006c617831717171717171717171717171717171717171717171717171717171717171717a67346573386c006c617831717171717171717171717171717171717171717171717171717171717171717078787678667100696e69740063726f73735f63616c6c5f65637265636f7665720063726f73735f63616c6c5f736861323536686173680063726f73735f63616c6c5f726970656d64313630686173680063726f73735f63616c6c5f64617461436f70790063726f73735f63616c6c5f6269674d6f644578700063726f73735f63616c6c5f626e3235364164640063726f73735f63616c6c5f626e3235365363616c61724d756c0063726f73735f63616c6c5f626e32353650616972696e67";

    public static String BINARY = BINARY_0;

    public static final String FUNC_CROSS_CALL_ECRECOVER = "cross_call_ecrecover";

    public static final String FUNC_CROSS_CALL_SHA256HASH = "cross_call_sha256hash";

    public static final String FUNC_CROSS_CALL_RIPEMD160HASH = "cross_call_ripemd160hash";

    public static final String FUNC_CROSS_CALL_DATACOPY = "cross_call_dataCopy";

    public static final String FUNC_CROSS_CALL_BIGMODEXP = "cross_call_bigModExp";

    public static final String FUNC_CROSS_CALL_BN256ADD = "cross_call_bn256Add";

    public static final String FUNC_CROSS_CALL_BN256SCALARMUL = "cross_call_bn256ScalarMul";

    public static final String FUNC_CROSS_CALL_BN256PAIRING = "cross_call_bn256Pairing";

    protected ContractCallPrecompile(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    protected ContractCallPrecompile(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }

    public static RemoteCall<ContractCallPrecompile> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(ContractCallPrecompile.class, web3j, credentials, contractGasProvider, encodedConstructor, chainId);
    }

    public static RemoteCall<ContractCallPrecompile> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(ContractCallPrecompile.class, web3j, transactionManager, contractGasProvider, encodedConstructor, chainId);
    }

    public static RemoteCall<ContractCallPrecompile> deploy(Web3j web3j, Credentials credentials, GasProvider contractGasProvider, BigInteger initialVonValue, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(ContractCallPrecompile.class, web3j, credentials, contractGasProvider, encodedConstructor, initialVonValue, chainId);
    }

    public static RemoteCall<ContractCallPrecompile> deploy(Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, BigInteger initialVonValue, Long chainId) {
        String encodedConstructor = WasmFunctionEncoder.encodeConstructor(BINARY, Arrays.asList());
        return deployRemoteCall(ContractCallPrecompile.class, web3j, transactionManager, contractGasProvider, encodedConstructor, initialVonValue, chainId);
    }

    public RemoteCall<String> cross_call_ecrecover(byte[] msgh, Uint8 v, byte[] r, byte[] s, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_ECRECOVER, Arrays.asList(msgh,v,r,s,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> cross_call_sha256hash(String in, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_SHA256HASH, Arrays.asList(in,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> cross_call_ripemd160hash(String in, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_RIPEMD160HASH, Arrays.asList(in,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> cross_call_dataCopy(String in, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_DATACOPY, Arrays.asList(in,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> cross_call_bigModExp(byte[] base, byte[] exponent, byte[] modulus, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_BIGMODEXP, Arrays.asList(base,exponent,modulus,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> cross_call_bn256Add(byte[] ax, byte[] ay, byte[] bx, byte[] by, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_BN256ADD, Arrays.asList(ax,ay,bx,by,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> cross_call_bn256ScalarMul(byte[] x, byte[] y, byte[] scalar, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_BN256SCALARMUL, Arrays.asList(x,y,scalar,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public RemoteCall<String> cross_call_bn256Pairing(String in, Uint64 value, Uint64 gas) {
        final WasmFunction function = new WasmFunction(FUNC_CROSS_CALL_BN256PAIRING, Arrays.asList(in,value,gas), String.class);
        return executeRemoteCall(function, String.class);
    }

    public static ContractCallPrecompile load(String contractAddress, Web3j web3j, Credentials credentials, GasProvider contractGasProvider, Long chainId) {
        return new ContractCallPrecompile(contractAddress, web3j, credentials, contractGasProvider, chainId);
    }

    public static ContractCallPrecompile load(String contractAddress, Web3j web3j, TransactionManager transactionManager, GasProvider contractGasProvider, Long chainId) {
        return new ContractCallPrecompile(contractAddress, web3j, transactionManager, contractGasProvider, chainId);
    }
}
