import Test.QuickCheck

data Direction = North | South | East | West
    deriving (Eq, Ord, Enum, Show)

(<<) :: Direction -> Direction -> Bool
(<<) x y = fromEnum x < fromEnum y

p_menor :: Direction -> Direction -> Bool
p_menor x y = (x < y) == (x<<y)
instance Arbitrary Direction where
    arbitrary = do
        n <- choose (0,3)
        return $ toEnum n