
# 🌍 Симуляция.
Консольная симуляция экосистемы с различными типами существ и растений, взаимодействующих в двумерном мире.
___
## Особенности
 Моделирование экосистемы с травоядными, хищниками и растениями

 Поиск пути с использованием алгоритма A*

 Система голода и здоровья для всех существ

 Интерактивное управление паузой и шагами симуляции

 Визуализация с помощью эмодзи

 Гибкая архитектура для добавления новых сущностей
___
## Установка и запуск
Требования
Java 17+, 
Maven 3.8+
```
bash
# Клонировать репозиторий
git clone https://github.com/mthbttrfl/Simulation.git
cd Simulation

# Собрать проект
mvn clean package

# Запустить симуляцию
java -jar target/Simulation-1.0-SNAPSHOT.jar
```
___
## Управление симуляцией

| Действие | Клавиши | Описание |
| -------- | ------- | -------- |
| Пауза/Продолжить | Enter | Переключение режима паузы |
| Шаг вперед	| Пробел (потом Enter) | Выполнить один шаг при паузе |
| Автоостановка |	 	| Симуляция останавливается, когда не остаётся живых существ |

___
## Настройка симуляции
Измените спрайты сущностей в классе Main.java:
```
java
public class Main {
    public static void main(String[] args) {
        SpriteRegister spriteRegister = new SpriteRegisterImpl();
        spriteRegister.add(Grass.class, "\uD83C\uDF31 ");
        spriteRegister.add(Rock.class, "\uD83E\uDEA8 ");
        spriteRegister.add(Tree.class, "🌳 ");
        spriteRegister.add(Wolf.class, "\uD83D\uDC3A ");
        spriteRegister.add(Beaver.class, "\uD83E\uDDAB ");
        spriteRegister.add(Rabbit.class, "\uD83D\uDC30 ");

        Menu menu = new Menu(spriteRegister);
        menu.run();
    }
}
```
___
## Структура проекта
```
src/
├── main/
│   ├── java/
│   │   ├── org.example/
│   │   │   ├── actions/          # Действия в мире
│   │   │   ├── coordinates/      # Система координат
│   │   │   ├── entities/         # Сущности экосистемы
│   │   │   │   ├── immovable/    # Неподвижные объекты
│   │   │   │   ├── movable/      # Подвижные существа
│   │   │   ├── factories/        # Фабрики объектов
│   │   │   ├── pathfinders/      # Алгоритмы поиска пути
│   │   │   ├── renders/          # Визуализация мира
│   │   │   ├── simulations/      # Управление симуляцией
│   │   │   ├── spawners/         # Генерация объектов
│   │   │   ├── validators/       # Валидация данных
│   │   │   ├── worlds/           # Реализация мира
|   |   |   ├── Menu.java         # Настройка запуска симуляции (меню)
│   │   │   └── Main.java         # Точка входа
```
___
## Ключевые компоненты
### Сущности
| Тип |	Классы	| Описание |
| --- | ------ | -------- |
| Травоядные |	Rabbit, Beaver |	Питаются растениями |
| Хищники |	Wolf |	Охотятся на травоядных |
| Растения |	Grass, Tree |	Источник пищи |
| Препятствия |	Rock |	Непроходимые объекты |

___
## Системные компоненты
| Компонент |	Назначение |
| --------- | ---------- |
| WorldMap |	Представление игрового мира |
| SimulationImpl |	Ядро управления симуляцией |
| AStarPathfinder |	Алгоритм интеллектуального поиска пути |
| RenderImpl |	Визуализация мира в консоли |
| Creature |	Базовый класс для всех существ |
| RandomSpawner |	Система генерации объектов |

___
## Расширение функционала
### Добавление новой сущности:
Создайте класс в `org.example.entities.movable`:
```
java
public class Fox extends Predator {
    public Fox(Coordinate coordinate, List<Class<? extends Entity>> targets) {
        super(coordinate, targets);
    }
}
```
Создайте фабрику в `org.example.factories.movable`:
```
java
public class FoxFactory extends MovableFactoryAncestor {
    public FoxFactory(Class<? extends Entity>... targets) {
        super(targets);
    }
    @Override
    public Entity get(Coordinate coordinate) {
        return new Fox(coordinate, List.of(targets));
    }
}
```
Зарегистрируйте в `Main.java`:
```
java
spriteRegister.add(Fox.class, "🦊 ");
```
Добавьте `FoxFactory` в фабрику для симуляции.
