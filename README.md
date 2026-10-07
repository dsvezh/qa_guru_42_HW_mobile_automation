# Запуск мобильных тестов

Стенд выбирается через `-DdeviceHost`: `browserstack`, `emulation` или `real`.
По умолчанию используется `browserstack`. Неизвестное значение вызывает ошибку.
Gradle передаёт системные свойства в JVM тестов.

Команды для PowerShell:

```powershell
.\gradlew.bat test "-DdeviceHost=browserstack"
.\gradlew.bat test "-DdeviceHost=emulation"
.\gradlew.bat test "-DdeviceHost=real" "-Ddevice.udid=SERIAL_FROM_ADB"
```

Для запуска только одного класса добавьте `--tests tests.SearchTests`
или `--tests tests.OnboardingTest`. На Linux/macOS используйте `./gradlew`.
iOS-тесты работают через существующий драйвер BrowserStack; при выборе
`emulation` или `real` они пропускаются. Локальные стенды предназначены для Android.

| Стенд | Драйвер | OWNER-конфиг | Настройки |
| --- | --- | --- | --- |
| `browserstack` | `BrowserstackDriver` | `BrowserStackConfig` | `src/test/resources/config.properties` |
| `emulation` | `EmulationDriver` | `EmulationConfig` | `src/test/resources/emulation.properties` |
| `real` | `RealDriver` | `RealConfig` | `src/test/resources/real.properties` |

Выбор стенда читает OWNER-конфиг `RunConfig`. Параметры командной строки имеют
приоритет над `src/test/resources/local.properties`, а локальный файл — над
настройками стенда. `local.properties` исключён из Git.
Прежний запуск `-Ddriver=local` также выбирает эмулятор; явно переданный
`deviceHost` имеет приоритет.

Для BrowserStack задайте `browserstack.user` и `browserstack.key` в
`local.properties` или через `-D`; укажите загруженное приложение в `android.app`
(для iOS — `ios.app`). Видео в Allure прикладывается только для BrowserStack.

Для локальных стендов запустите Appium с драйвером UiAutomator2.
В `emulation.properties` сохранены текущие настройки эмулятора:
`Medium_Phone_API_37.0`, Android `17`. При необходимости измените их:

```powershell
.\gradlew.bat test "-DdeviceHost=emulation" "-Ddevice.name=My_AVD" "-Ddevice.avd=My_AVD" "-Ddevice.platformVersion=14"
```

`device.avd` позволяет Appium запустить выбранный AVD. Чтобы использовать уже
запущенный эмулятор, передайте его `device.udid` из `adb devices`; при этом AVD
не передаётся. Для реального телефона включите отладку по USB, разрешите
подключение и обязательно задайте `device.udid` из `adb devices`.
В `real.properties` версия Android пустая: Appium определяет её самостоятельно.

Общие локальные параметры: `appium.url`, `device.name`, `device.platformVersion`,
`device.udid`, `app.path`, `app.url`, `app.package`, `app.activity`.
При отсутствии APK по `app.path` драйвер скачивает его по `app.url`.
Для другого адреса Appium используйте, например, `"-Dappium.url=http://localhost:4723/wd/hub"`.

Проверка конфигурации без Appium и BrowserStack:

```powershell
.\gradlew.bat test --tests drivers.DriverConfigurationTest
```

Пример структуры драйверов: [qa_guru_course_mobile](https://github.com/Dmitry5401/qa_guru_course_mobile/tree/master/src/test/java/drivers).
