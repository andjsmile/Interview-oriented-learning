
### 骨干代码

```cpp
class GameCharacter;  // 类的前置声明  forward declaration
class HealthCalcFunc
{
public:
    ...
    virtual int calc(const GameCharacter &gc) const
    {
        // 省略
    }
    ...
};
HealthCalcFunc defaultHealthCalc;

class GameCharacter
{
public:
    explicit GameCharacter(HealthCalcFunc *phcf=&defaultHealthCalc)
    :pHealthCalc(phcf)
    {}

    int healthValue() const
    {
        return pHealthCalc->(*this);
    }
    ...
private:
    HealthCalcFunc* pHealthCalc;
};
```