# 开发规范

本文档供 **AI 编码助手** 阅读。

## 环境

- Java 21、Maven 3.9.6、Spring Boot 4.1.1
- 本地默认端口：`48791`（见 `application.yaml`）
- Docker 容器端口：`8080`（映射主机端口 `48791`）
- 默认激活 Profile：`prod`（可通过环境变量 `SPRING_PROFILES_ACTIVE` 覆盖）

## 命令

```bash
mvn spring-boot:run
mvn test
mvn clean package -DskipTests
```

构建产物：`target/spring-profile-demo.jar`。CI 构建使用 Dragonwell JDK 21。

## 目录结构

```
src/main/java/cn/netbuffer/spring/profile/demo/
├── SpringProfileDemoApplication.java       # 应用启动入口
└── controller/
    └── ConfigController.java              # 配置读取与对外接口
src/main/resources/
├── application.yaml                       # 主配置，导入模块配置
├── application-dev.yaml                   # dev 环境配置
├── application-prod.yaml                  # prod 环境配置
└── module/                                # 模块化配置
    ├── sys-dev.yaml / sys-prod.yaml
    └── order-dev.yaml / order-prod.yaml
src/test/java/                             # SpringBootTest 测试用例
help/                                      # HTTP 请求示例与辅助文档
```

## 核心约定

- 命名风格：`*Controller`、`*Application`、`*ApplicationTests`
- Profile 机制：遵循 Spring Boot 4 多环境约定，通过 `spring.config.import` 加载 `module/` 下子模块配置
- 环境变量覆盖：遵循 Spring Boot Relaxed Binding 规则（点转下划线、短横线移除/转下划线、大写），如 `ai.model.openai.api-key` 对应 `AI_MODEL_OPENAI_API_KEY` 或 `AI_MODEL_OPENAI_APIKEY`
- 保持轻量示例规模：避免无必要引入复杂中间件或安全组件
- 不要随意改动 Java/Spring Boot 大版本与基础包结构

## 联改规则

| 改动 | 同步更新 |
|------|----------|
| 新增/修改配置属性 | `src/main/resources/application*.yaml`、`ConfigController.java`、`help/http-requests.http` |
| 端口改动 | `application.yaml`、`Dockerfile`、`docker-compose.yml`、`README.md`、`help/http-requests.http` |
| 依赖与插件变更 | `pom.xml`、`README.md` |

## 提交规范

遵循 Conventional Commits：`<type>: <说明>`（英文祈使句、小写开头、无句末句号）。

类型推荐：`feat` `fix` `docs` `refactor` `test` `chore` `perf`

- 一事一提交；相关文档/HTTP 示例可在同个提交中更新
- 用户未明确要求时禁止直接 `git commit` / `git push`
- 禁止提交任何生产密钥、Token 或敏感账号信息

## CI / 发布

`.github/workflows/build.yml`：
- 推送或 PR 到 `master` / `main` 分支时触发构建、单元测试并上传 jar 包 Artifact。
- 推送 `v*` 标签时自动创建 GitHub Release 并构建推送镜像到 `ghcr.io`。

## 安全与边界

- 不把真实密钥/敏感信息硬编码到代码或配置文件中
- 保持改动聚焦，严禁脱离需求的非必要大重构
