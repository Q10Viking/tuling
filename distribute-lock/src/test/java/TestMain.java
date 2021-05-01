public class TestMain {
    public static void main(String[] args) {

        String[] children = {
                "r-000",
                "r-001",
                "w-002",
                "r-003",
                "r-004",
                "w-005",
                "w-006",
                "r-007",
        };

        int         index = 0;
        int         firstWriteIndex = Integer.MAX_VALUE;
        int         ourIndex = -1;
        for ( String node : children )
        {
            if ( node.contains("w") )
            {
                firstWriteIndex = Math.min(index, firstWriteIndex);
            }
            else if ( node.startsWith("r-007") )
            {
                ourIndex = index;
                break;
            }

            ++index;
        }

        System.out.println(children[firstWriteIndex]);


    }
}
