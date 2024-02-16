#version 330 core
out vec4 fragColor;

in vec2 textureCoord;
in vec4 col;

uniform sampler2D ourTexture;

float handleCoord(float c, float dc)
{
    float result = 0;

    // calculate span of texel
    float min = c - 0.5 * dc - 0.5;
    float max = c + 0.5 * dc - 0.5;

    // get floor values
    float floorMin = floor(min);
    float floorMax = floor(max);

    // if the span is inside a single texel
    if (floorMin == floorMax) min = 0;

    // casey's equation
    result = (floorMax - 1) + (floorMax + min) / dc;

    return result;
}

void main()
{
    vec2 atlasSize = vec2(512,512);

    // uv coords times atlas size to get texel space coords
    vec2 uv = textureCoord * atlasSize;

    // calculate size of a texel
    vec2 duv = 1.0 / atlasSize;

    uv = vec2(handleCoord(uv.x, duv.x), handleCoord(uv.y, duv.y));

    // divide back the uv coords to normalized space
    fragColor = texture(ourTexture, uv / atlasSize) * col;
}