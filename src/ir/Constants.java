package ir;

import ir.DerivedTypes.*;

import java.util.ArrayList;

public class Constants {
    public static MyContext context = MyContext.getInstance();

    /// Base class for constants with no operands.
    ///
    /// These constants have no operands; they represent their data directly.
    /// Since they can be in use by unrelated modules (and are never based on
    /// GlobalValues), it never makes sense to RAUW them.
    public static class ConstantData extends Constant {
        public ConstantData(Type ty) {
            super(ty, 0);
        }
    }

    //===----------------------------------------------------------------------===//
    /// This is the shared class of boolean and integer constants. This class
    /// represents both boolean and integral constants.
    /// Class for constant integers.
    public static class ConstantInt extends ConstantData {
        private int val;

        private ConstantInt(IntegerType ty, int val) {
            super(ty);
            this.val = val;
            context.IntConstants.put(val, this);
        }

        /**
         * 获取一个值为v的ConstantInt对象，若MyContext中已存在，则直接返回
         */
        public static ConstantInt get(int v) {
            return context.IntConstants.getOrDefault(v, new ConstantInt(Type.getIntegerTy(), v));
        }

        /**
         * 常量0
         */
        public static ConstantInt const_0() {
            return get(0);
        }

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public void destroy() {
            context.IntConstants.remove(this.val);
        }
    }

    //===----------------------------------------------------------------------===//
    /// ConstantFP - Floating Point Values [float, double]
    ///
    public static class ConstantFP extends ConstantData {
        private float val;

        private ConstantFP(Type ty, float val) {
            super(ty);
            this.val = val;
            context.FPConstants.put(val, this);
        }

        /**
         * 获取一个值为v的ConstantFP对象，若MyContext中已存在，则直接返回
         */
        public static ConstantFP get(float v) {
            return context.FPConstants.getOrDefault(v, new ConstantFP(Type.getFloatTy(), v));
        }

        /**
         * 浮点数常量0
         */
        public static ConstantFP const_0() {
            return get(0);
        }

        public float getVal() {
            return val;
        }

        public void setVal(float val) {
            this.val = val;
        }

        public void destroy() {
            context.FPConstants.remove(this.val);
        }
    }

    //===----------------------------------------------------------------------===//
    /// All zero aggregate value
    ///
    public static class ConstantAggregateZero extends ConstantData {
        private ConstantAggregateZero(Type ty) {
            super(ty);
            //TODO:存入MyContext
        }

        public static ConstantAggregateZero get(Type ty) {
            assert ty.isArrayTy() || ty.isVectorTy();
            //TODO:检查是否存在
            return null;
        }
    }

    /// Base class for aggregate constants (with operands).
    ///
    /// These constants are aggregates of other constants, which are stored as
    /// operands.
    ///
    /// Subclasses are \a ConstantStruct, \a ConstantArray, and \a
    /// ConstantVector.
    ///
    /// \note Some subclasses of \a ConstantData are semantically aggregates --
    /// such as \a ConstantDataArray -- but are not subclasses of this because they
    /// use operands.
    public static class ConstantAggregate extends Constant {
        private ConstantAggregate(Type ty, ArrayList<Value> V) {
            super(ty, V);
            for (Value v : V) {
                assert ty == v.getType();
            }
            //TODO:存入MyContext
        }

        public static ConstantAggregate get(Type ty, ArrayList<Value> V) {
            //TODO:检查是否存在
            return new ConstantAggregate(ty, V);
        }
    }

    //===----------------------------------------------------------------------===//
    /// ConstantArray - Constant Array Declarations
    ///
    public static class ConstantArray extends ConstantAggregate {
        public ConstantArray(ArrayType ty, ArrayList<Value> V) {
            super(ty, V);
            //TODO:存入MyContext
        }

        public static Constant get(ArrayType ty, ArrayList<Value> V) {
            if(V.isEmpty()){
                return ConstantAggregateZero.get(ty);
            }
            //TODO:检查是否存在
            return null;
        }
    }


}
