# NoteBlockAPI (Nukkit-MOT 移植版)

红石音乐 API 插件，移植自 Nukkit-EC 的 NoteblockAPI，面向 Nukkit-MOT。

## 使用方法

```java
Song song = NBSDecoder.parse(new File("plugins/NoteBlockAPI/songs/example.nbs"));
RadioSongPlayer player = new RadioSongPlayer(song);
player.addPlayer(server.getPlayer("Steve"));
player.setPlaying(true);
```

## 构建

1. 先构建并安装 Nukkit-MOT 到本地仓库（插件依赖 `cn.nukkit:Nukkit:MOT-SNAPSHOT`）：

```
cd Nukkit-MOT
mvn install -DskipTests
```

2. 枻建插件：

```
cd NoteblockAPI
mvn package
```

产物为 `target/NoteBlockAPI-1.4.0.jar`，放入服务器 `plugins/` 目录即可。

## 移植说明

- 移除了 SynapseAPI 依赖与 EC 的 RakNetReliability 发包逻辑，改为 MOT 原生 `Player#dataPacket`，
  编码按玩家协议版本自动完成（无需手动 tryEncode）。
- 音符通过 `LevelSoundEventPacket`（SOUND_NOTE，extraData = instrument << 8 | pitch）+
  `BlockEventPacket` 双通道下发，与 MOT 原生音符盒行为一致；
  低音（pitch < 0）与自定义乐器走 `PlaySoundPacket`。
- EC 的 `SoundEnum` 替换为 MOT 的 `cn.nukkit.level.Sound`（配合为 MOT 新增的 `Sound#fromName`）。
- 依赖 MOT 新增的 `Player#getProtocol()`。
