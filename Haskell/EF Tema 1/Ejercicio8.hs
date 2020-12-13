import Test.QuickCheck

unEuro :: Double
unEuro = 166.386

pesetasAEuros :: Double -> Double
pesetasAEuros x = x/unEuro

eurosAPesetas :: Double -> Double
eurosAPesetas x = x*unEuro


p_inversas x = eurosAPesetas (pesetasAEuros x) == x