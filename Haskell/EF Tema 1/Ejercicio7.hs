import Test.QuickCheck

type TotalSeg = Integer
type Horas = Integer
type Minutos = Integer
type Segundos = Integer

descomponer :: TotalSeg -> (Horas, Minutos, Segundos)
descomponer x = (horas, minutos, segundos)
    where
        horas = div x 3600
        minutos = div (mod x 3600) 60
        segundos = mod (mod x 3600) 60



entre :: Ord a => a -> (a,a) -> Bool
entre x (me,ma)
    |me<=x&&x<=ma = True
    |otherwise = False


p_descomponer x = x>=0 ==> h*3600+m*60+s == x
                            && entre m (0,59)
                            && entre s (0,59)
    where (h,m,s) = descomponer x