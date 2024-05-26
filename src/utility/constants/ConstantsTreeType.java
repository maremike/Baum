package utility.constants;


public interface ConstantsTreeType
{
    float CONFIDENCE_VALUE_SPLITTER = 0f; // If certain trees confidence percentage exceeds this number, it will be deciduous

    String[] popularConiferousTrees = {"picea", "pinus", "taxus", "abies", "juniperus", "larix", "cupressus", "thuja",
            "cryptomeria", "pseudotsuga", "tsuga", "pseudolarixamabilis", "cedrus", "sequoioidae", "picea abies",
            "abies nordmanniana", "larix decidua", "pinus mugo", "juniperus communis", "picea pungens",
            "taxus baccata", "thuja plicata", "cedrus atlantica"};
    String[] popularDeciduousTrees = {"acer campestre", "acer palmatum", "acer pseudoplatanus", "quercus", "ulmus",
            "betula", "malus domestica", "pyrus", "prunus domestica", "prunus avium", "juglans regia", "castanea",
            "prunus dulcis", "corylus colurna", "fagus sylvatica", "betula pendula", "tilia platyphyllos",
            "alnus glutinosa", "malus domestica", "castanea sativa", "fraxinus exelsior"};

    // Values taken from the average traits of 6 of the most popular types of among deciduous and coniferous trees
    float decidiuousDeterminingCroneDiameterM = 1.75f;
    float decidiuousDeterminingCircumferenceCM = 37.17f;
    float coniferousDeterminingCroneDiameterM = 1.53f;
    float coniferousDeterminingCircumferenceCM = 63.61f;
    float coniferousHeightM = 8f;
    float decidiuousHeightM = 6.47f;
}
